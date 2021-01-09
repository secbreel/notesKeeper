package com.secbreel.notes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.secbreel.notes.R

class CategoryScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_screen)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}