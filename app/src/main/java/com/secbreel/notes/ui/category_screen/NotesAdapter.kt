package com.secbreel.notes.ui.category_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.secbreel.notes.R
import com.secbreel.notes.model.ListItem

class NotesAdapter(
    private val itemsList: List<ListItem> = listOf(),
    private val bind: (View, ListItem) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == ListItem.TYPE_DATE) {
            val view: View = inflater.inflate(R.layout.item_note_date, parent, false)

            NotesDateViewHolder(view)
        } else {
            val view: View = inflater.inflate(R.layout.item_note, parent, false)

            NoteViewHolder(view)
        }
    }


        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val item = itemsList[position]
            when(itemsList[position].getType()) {
                ListItem.TYPE_DATE -> {
                    //val convertedHolder : NotesDateViewHolder = holder as NotesDateViewHolder
                    bind(holder.itemView, item)
                }
                ListItem.TYPE_NOTE -> {
                    bind(holder.itemView , item)
                }
            }




        }

        override fun getItemCount(): Int = itemsList.count()

    override fun getItemViewType(position: Int): Int {
        return itemsList[position].getType()
    }

        class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        }

        class NotesDateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        }
    }