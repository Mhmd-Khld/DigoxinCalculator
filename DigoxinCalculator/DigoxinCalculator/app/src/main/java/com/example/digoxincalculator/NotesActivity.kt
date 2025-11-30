package com.example.digoxincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.content.Intent // تم إضافته لفتح الأنشطة
import android.widget.ImageView // تم إضافته للتعامل مع الصورة القابلة للضغط

class NotesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        // تفعيل زر الرجوع في الـ ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Clinical Guidelines" // تم تعديل العنوان ليتناسب مع الـ UI

        val kidneyImage: ImageView = findViewById(R.id.kidneyImage)

        // إضافة مستمع الضغط على الصورة
        kidneyImage.setOnClickListener {
            // فتح صفحة "KidneyDisordersActivity"
            val intent = Intent(this, KidneyDisordersActivity::class.java)
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