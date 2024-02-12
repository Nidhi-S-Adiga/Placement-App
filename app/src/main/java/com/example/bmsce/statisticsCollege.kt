package com.example.bmsce

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class statisticsCollege : AppCompatActivity() {
    private var userEmail: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.statistics)

        userEmail = intent.getStringExtra("userEmail")
        if (userEmail == null) {
            userEmail = "No message found"
        }

        val imageView1: ImageView = findViewById(R.id.imageView1)
        val imageView2: ImageView = findViewById(R.id.imageView2)

        imageView1.setImageResource(R.drawable.placementtable)
        imageView2.setImageResource(R.drawable.placementgraph)
    }

    fun goBack(view: View?) {
        val userType = intent.getStringExtra("userType")
        val intent: Intent
        if ("Student" == userType) {
            intent = Intent(this, StudentHome::class.java)
        } else if ("TPO" == userType) {
            intent = Intent(this, TPOHome::class.java)
            intent.putExtra("userEmail", userEmail)
        } else {
            throw IllegalArgumentException("Invalid user type")
        }
        startActivity(intent)
        finish()
    }
}
