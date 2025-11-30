package com.example.digoxincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

class GITDisordersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ⭐ تأكد أن activity_git_disorders.xml موجود ⭐
        setContentView(R.layout.activity_git_disorders)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "GIT Disorders Notes"
    }
    // ... (باقي كود onOptionsItemSelected)
}