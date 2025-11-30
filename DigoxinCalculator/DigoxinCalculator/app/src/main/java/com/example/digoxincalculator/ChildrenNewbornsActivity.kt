package com.example.digoxincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

class ChildrenNewbornsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_children_newborns)

        // إعداد الـ ActionBar لزر الرجوع
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Children & Newborns Dosage" // عنوان هذه الشاشة
    }

    // لتفعيل زر الرجوع في الـ ActionBar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}