package com.example.bmsce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
class statisticsCollege : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.statistics)

        val imageView1: ImageView = findViewById(R.id.imageView1)
        val imageView2: ImageView = findViewById(R.id.imageView2)

        imageView1.setImageResource(R.drawable.placementtable)
        imageView2.setImageResource(R.drawable.placementgraph)
    }
}
