package com.example.digoxincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.content.Intent
import android.widget.ImageView

class NotesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        // تفعيل زر الرجوع في الـ ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Clinical Guidelines Menu" // تم تعديل العنوان

        val kidneyImage: ImageView = findViewById(R.id.kidneyImage)

        // ⭐⭐ 1. تعريف الصورة الجديدة: Elderly Dosing ⭐⭐
        val elderlyDosingImage: ImageView = findViewById(R.id.elderlyDosingImage)

        // إضافة مستمع الضغط على الصورة القديمة (Kidney)
        kidneyImage.setOnClickListener {
            // فتح صفحة "KidneyDisordersActivity"
            val intent = Intent(this, KidneyDisordersActivity::class.java)
            startActivity(intent)
        }

        // ⭐⭐ 2. إضافة مستمع الضغط على الصورة الجديدة ⭐⭐
        elderlyDosingImage.setOnClickListener {
            // فتح الصفحة المستقلة الجديدة: "ElderlyDosingActivity"
            val intent = Intent(this, ElderlyDosingActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // الـ home هو زر الرجوع (Up) الذي فعلناه
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}