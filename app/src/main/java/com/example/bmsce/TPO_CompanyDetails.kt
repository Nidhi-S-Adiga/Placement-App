package com.example.bmsce

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.bmsce.models.CompanyModel
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase

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
    private lateinit var btnUpdate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tpo_company_details)

        userEmail = intent.extras?.getString("userEmail")?:"No message found"

        initView()
        setValuesToViews()

        tvCmpLink.setTextIsSelectable(true)

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("cmpId").toString(),
                intent.getStringExtra("cmpname").toString(),
            )
        }
    }

    private fun openUpdateDialog(
        cmpId: String,
        cmpname: String,
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflator = layoutInflater
        val mDialogView = inflator.inflate(R.layout.update_dialog,null)

        mDialog.setView(mDialogView)

        val companyNameEditText = mDialogView.findViewById<EditText>(R.id.companyNameEditText)
        val roleEditText = mDialogView.findViewById<EditText>(R.id.roleEditText)
        val packageEditText = mDialogView.findViewById<EditText>(R.id.packageEditText)
        val updateButton = mDialogView.findViewById<Button>(R.id.updateButton)

        companyNameEditText.setText(intent.getStringExtra("cmpname").toString())
        roleEditText.setText(intent.getStringExtra("cmprole").toString())
        packageEditText.setText(intent.getStringExtra("cmppackage").toString())

        mDialog.setTitle("Updating $cmpname details")

        val alertDialog = mDialog.create()
        alertDialog.show()

        updateButton.setOnClickListener{
            updateCmpData(
                cmpId,
                companyNameEditText.text.toString(),
                roleEditText.text.toString(),
                packageEditText.text.toString()
            )
            Toast.makeText(applicationContext, "Company Data Uploaded", Toast.LENGTH_LONG).show()
            tvCmpName.text = companyNameEditText.text.toString()
            tvCmpRole.text = roleEditText.text.toString()
            tvCmpPackage.text = packageEditText.text.toString()

            alertDialog.dismiss()
        }
    }

    private fun updateCmpData(
        id:String,
        name: String,
        role: String,
        pack: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("company").child(id)
        val cmpInfo= CompanyModel(id,name,role,pack)
        dbRef.setValue(cmpInfo)
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
        btnUpdate = findViewById(R.id.update)
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