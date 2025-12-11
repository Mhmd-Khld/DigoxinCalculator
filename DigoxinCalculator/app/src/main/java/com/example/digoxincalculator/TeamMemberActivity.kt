package com.example.digoxincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TeamMemberActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_team_member)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Team Member"
    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}