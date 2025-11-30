package com.example.digoxincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem // لغرض زر الرجوع

class NotesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes) // سنقوم بإنشاء هذا الملف لاحقًا

        // تفعيل زر الرجوع في الـ ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "الملاحظات" // عنوان الصفحة
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed() // الرجوع للصفحة السابقة
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}