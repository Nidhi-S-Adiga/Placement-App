package com.example.bmsce

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TPO_CompanyDetails : AppCompatActivity(){
    private lateinit var tvCmpId: TextView
    private lateinit var tvCmpName: TextView
    private lateinit var tvCmpRole: TextView
    private lateinit var tvCmpPackage: TextView
    private lateinit var tvCmpLink: TextView
    private lateinit var tvpSpreadsheet: TextView
    private lateinit var userEmail:String
    private lateinit var shareForm: Button
    private lateinit var shareSheet: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tpo_company_details)

        userEmail = intent.extras?.getString("userEmail")?:"No message found"

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
        tvpSpreadsheet= findViewById(R.id.tvSpreadId)
        shareForm= findViewById(R.id.shareForm)
        shareSheet= findViewById(R.id.shareSheet)
    }

    private fun setValuesToViews() {
        tvCmpId.text = intent.getStringExtra("cmpId")
        tvCmpName.text = intent.getStringExtra("cmpname")
        tvCmpRole.text = intent.getStringExtra("cmprole")
        tvCmpPackage.text = intent.getStringExtra("cmppackage")
        val companyLink = intent.getStringExtra("cmplink")
        tvCmpLink.text = "Click here!"

        val spreadLink = intent.getStringExtra("cmpSpread")
        tvpSpreadsheet.text = "Click here!"

        tvpSpreadsheet.setOnClickListener {
            // Create an intent to open the link in a web browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(spreadLink))
            startActivity(intent)
        }

        tvCmpLink.setOnClickListener {
            // Create an intent to open the link in a web browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(companyLink))
            startActivity(intent)
        }

        shareForm.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            val shareHead = "Form Link"
            val hello = companyLink
            intent.putExtra(Intent.EXTRA_SUBJECT, shareHead)
            intent.putExtra(Intent.EXTRA_TEXT, "Find the Form link")
            intent.putExtra(Intent.EXTRA_TEXT, hello)
            startActivity(Intent.createChooser(intent, "Share Using"))
        }

        shareSheet.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            val shareHead = "Spreadsheet Link"
            intent.putExtra(Intent.EXTRA_SUBJECT, shareHead)
            intent.putExtra(Intent.EXTRA_TEXT, spreadLink)
            startActivity(Intent.createChooser(intent, "Share Using"))
        }
    }

    fun backClicked(view: View){
        val intent = Intent(baseContext, TPOfetch::class.java)
        intent.putExtra("userEmail", userEmail)
        startActivity(intent)
        finish()
    }

    fun addCmpClicked(view: View){
        val intent = Intent(baseContext, AddCompany::class.java)
        intent.putExtra("userEmail", userEmail)
        startActivity(intent)
        finish()
    }

}