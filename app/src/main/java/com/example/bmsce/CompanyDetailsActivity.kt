package com.example.bmsce

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CompanyDetailsActivity : AppCompatActivity(){
    private lateinit var tvCmpId: TextView
    private lateinit var tvCmpName: TextView
    private lateinit var tvCmpRole: TextView
    private lateinit var tvCmpPackage: TextView
    private lateinit var tvCmpLink: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_details)

        initView()
        setValuesToViews()

        tvCmpLink.setTextIsSelectable(true)

    }

    private fun initView() {
        tvCmpId = findViewById(R.id.tvCmpId)
        tvCmpName = findViewById(R.id.tvCmpName)
        tvCmpRole = findViewById(R.id.tvCmpRole)
        tvCmpPackage = findViewById(R.id.tvCmpPackage)
        tvCmpLink = findViewById(R.id.tvCmpLink)
    }

    private fun setValuesToViews() {
        tvCmpId.text = intent.getStringExtra("cmpId")
        tvCmpName.text = intent.getStringExtra("cmpname")
        tvCmpRole.text = intent.getStringExtra("cmprole")
        tvCmpPackage.text = intent.getStringExtra("cmppackage")
        val companyLink = intent.getStringExtra("cmplink")
        tvCmpLink.text = "Click here!"

        tvCmpLink.setOnClickListener {
            // Create an intent to open the link in a web browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(companyLink))
            startActivity(intent)
        }
    }

    fun backClicked(view: View){
        val intent = Intent(baseContext, FetchingActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun homeClicked(view: View){
        val intent = Intent(baseContext, StudentHome::class.java)
        startActivity(intent)
        finish()
    }
}