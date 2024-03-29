package com.example.bmsce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val studentCardView = findViewById<androidx.cardview.widget.CardView>(R.id.studentCardView)

        // Set click listener for the student CardView
        studentCardView.setOnClickListener {
            // Start StudentLogin activity
            val intent = Intent(baseContext, StudentLogin::class.java)
            startActivity(intent)
        }
    }

    fun tpoClicked(view: View)
    {
        val intent = Intent(baseContext, TPOLogin::class.java)
        startActivity(intent)
    }
}