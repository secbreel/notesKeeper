package com.secbreel.notes.ui.screens.category_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.secbreel.notes.databinding.ItemNoteBinding
import com.secbreel.notes.databinding.ItemNoteDateBinding
import com.secbreel.notes.model.DateItem
import com.secbreel.notes.model.ListItem
import com.secbreel.notes.model.Note

class NotesAdapter(
    private val itemsList: List<ListItem> = listOf(),
    private val navigateToNote : (Note) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == ListItem.TYPE_DATE) {
            val viewBinding = ItemNoteDateBinding.inflate(inflater, parent, false)

            NotesDateViewHolder(viewBinding)
        } else {
            val viewBinding = ItemNoteBinding.inflate(inflater, parent, false)

            NoteViewHolder(viewBinding)
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemsList[position]
        when (itemsList[position].getType()) {
            ListItem.TYPE_DATE -> {
                val dateItem: DateItem = item as DateItem
                (holder as NotesDateViewHolder).itemViewBinding.notesDate.text = dateItem.date
            }
            ListItem.TYPE_NOTE -> {
                val note: Note = item as Note
                (holder as NoteViewHolder).itemViewBinding.noteName.text = note.title
                holder.itemViewBinding.noteCreationDate.text =
                    note.date
                holder.itemViewBinding.root.setOnClickListener {
                    navigateToNote(note)
                }
            }
        }


    }

    override fun getItemCount(): Int = itemsList.count()

    override fun getItemViewType(position: Int): Int {
        return itemsList[position].getType()
    }

    class NoteViewHolder(val itemViewBinding: ItemNoteBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    class NotesDateViewHolder(val itemViewBinding: ItemNoteDateBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)
}