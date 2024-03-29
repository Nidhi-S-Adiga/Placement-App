package com.example.bmsce

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bmsce.models.CompanyModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class AddCompany: AppCompatActivity() {
    private lateinit var companyName:EditText
    private lateinit var role:EditText
    private lateinit var packageGiven:EditText
    private lateinit var link:EditText
    private lateinit var addbtn:Button

    private lateinit var dbRef: DatabaseReference
    private lateinit var userEmail:String
    private lateinit var spreadsheet: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addcompany)

        userEmail = intent.extras?.getString("userEmail")?:"No message found"

        companyName = findViewById(R.id.Companyname)
        role = findViewById(R.id.Roleoffered)
        packageGiven = findViewById(R.id.Package)
        link = findViewById(R.id.LinktoApply)
        spreadsheet = findViewById(R.id.spreadsheet)
        addbtn = findViewById(R.id.add)


        dbRef = FirebaseDatabase.getInstance().getReference("company")

        addbtn.setOnClickListener {
            saveCompanyData()
        }
    }
    private fun saveCompanyData(){
        //getting values
        val cmpname = companyName.text.toString()
        val cmprole=role.text.toString()
        val cmppackage=packageGiven.text.toString()
        val cmplink=link.text.toString()
        val spreadsheetValue = spreadsheet.text.toString()


        if(cmpname.isEmpty()){
            companyName.error="Please enter company name"
            return
        }
        if(cmprole.isEmpty()){
            role.error="Please enter role offered"
            return
        }
        if(cmppackage.isEmpty()){
            packageGiven.error="Please enter package offered"
            return
        }
        if(cmplink.isEmpty()){
            link.error="Please enter company registration link"
            return
        }
        if(spreadsheetValue.isEmpty()){
            spreadsheet.error="Please enter company spreadsheet link"
            return
        }

        val cmpId = dbRef.push().key!!

        val company = CompanyModel(cmpId,cmpname,cmprole,cmppackage,cmplink,spreadsheetValue)

        dbRef.child(cmpId).setValue(company).addOnCompleteListener{
            Toast.makeText(this,"Data inserted successfully",Toast.LENGTH_LONG).show()

            companyName.text.clear()
            role.text.clear()
            packageGiven.text.clear()
            link.text.clear()
            spreadsheet.text.clear()

        }.addOnFailureListener{err->
            Toast.makeText(this,"Error ${err.message}",Toast.LENGTH_LONG).show()
        }
    }

    fun company_clicked(view: View){
        val intent = Intent(baseContext, TPOfetch::class.java)
        intent.putExtra("userEmail", userEmail)
        startActivity(intent)
        finish()
    }

    fun homeClicked(view: View){
        val intent = Intent(baseContext, TPOHome::class.java)
        intent.putExtra("userEmail", userEmail)  // Add userEmail to the intent again
        startActivity(intent)
        finish()
    }


}