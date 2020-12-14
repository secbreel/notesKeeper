package com.secbreel.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast

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
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            findViewById<ImageView>(R.id.categoryIcon).setImageURI(data?.data)
        }
    }

}