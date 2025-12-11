package com.example.digoxincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.content.Intent
import android.widget.ImageView

class NotesActivity : AppCompatActivity() {
    private lateinit var electrolytesImage: ImageView
    private lateinit var gitDisordersImage: ImageView
    private lateinit var pregnancyImage: ImageView
    private lateinit var childrenNewbornsImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Clinical Guidelines Menu"

        val kidneyImage: ImageView = findViewById(R.id.kidneyImage)
        val elderlyDosingImage: ImageView = findViewById(R.id.elderlyDosingImage)


        electrolytesImage = findViewById(R.id.electrolytesImage)
        gitDisordersImage = findViewById(R.id.gitDisordersImage)

        pregnancyImage = findViewById(R.id.pregnancyImage)
        childrenNewbornsImage = findViewById(R.id.childrenNewbornsImage)


        kidneyImage.setOnClickListener {
            val intent = Intent(this, KidneyDisordersActivity::class.java)
            startActivity(intent)
        }


        elderlyDosingImage.setOnClickListener {
            val intent = Intent(this, ElderlyDosingActivity::class.java)
            startActivity(intent)
        }


        electrolytesImage.setOnClickListener {
            val intent = Intent(this, ElectrolytesActivity::class.java)
            startActivity(intent)
        }


        gitDisordersImage.setOnClickListener {
            val intent = Intent(this, GITDisordersActivity::class.java)
            startActivity(intent)
        }


        pregnancyImage.setOnClickListener {
            val intent = Intent(this, PregnancyActivity::class.java)
            startActivity(intent)
        }

        childrenNewbornsImage.setOnClickListener {
            val intent = Intent(this, ChildrenNewbornsActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}