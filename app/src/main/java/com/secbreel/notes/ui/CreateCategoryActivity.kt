package com.secbreel.notes.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.secbreel.notes.R
import com.secbreel.notes.model.Category
import com.secbreel.notes.persistance.CategoryRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CreateCategoryActivity : AppCompatActivity() {
    val PICK_IMAGE = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_category)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        findViewById<ImageView>(R.id.categoryIcon).setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE)
        }

        val categoryRepository = CategoryRepository(this)
        findViewById<Button>(R.id.button).setOnClickListener {
            val category = Category(findViewById<EditText>(R.id.editCategoryTitle).text.toString(), 0)
            categoryRepository.insert(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete { finish() }
                .subscribe()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            findViewById<ImageView>(R.id.categoryIcon).setImageURI(data?.data)
        }
    }

}