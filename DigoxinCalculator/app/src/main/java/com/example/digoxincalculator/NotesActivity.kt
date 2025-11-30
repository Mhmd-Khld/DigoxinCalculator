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

        // تفعيل زر الرجوع في الـ ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Clinical Guidelines Menu"

        val kidneyImage: ImageView = findViewById(R.id.kidneyImage)
        val elderlyDosingImage: ImageView = findViewById(R.id.elderlyDosingImage)

        // ربط الصور الجديدة
        electrolytesImage = findViewById(R.id.electrolytesImage)
        gitDisordersImage = findViewById(R.id.gitDisordersImage)
        // ⭐⭐ الحل هنا: ربط الصورة الخامسة بالـ ID الصحيح ⭐⭐
        pregnancyImage = findViewById(R.id.pregnancyImage)
        childrenNewbornsImage = findViewById(R.id.childrenNewbornsImage)

        // إضافة مستمع الضغط على الصورة القديمة (Kidney)
        kidneyImage.setOnClickListener {
            val intent = Intent(this, KidneyDisordersActivity::class.java)
            startActivity(intent)
        }

        // إضافة مستمع الضغط على صورة Elderly Dosing
        elderlyDosingImage.setOnClickListener {
            val intent = Intent(this, ElderlyDosingActivity::class.java)
            startActivity(intent)
        }

        // مستمع الضغط لصورة Electrolytes
        electrolytesImage.setOnClickListener {
            val intent = Intent(this, ElectrolytesActivity::class.java)
            startActivity(intent)
        }

        // مستمع الضغط لصورة GIT Disorders
        gitDisordersImage.setOnClickListener {
            val intent = Intent(this, GITDisordersActivity::class.java)
            startActivity(intent)
        }

        // مستمع الضغط لصورة Pregnancy
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