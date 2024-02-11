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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addcompany)

        userEmail = intent.extras?.getString("userEmail")?:"No message found"

        companyName = findViewById(R.id.Companyname)
        role = findViewById(R.id.Roleoffered)
        packageGiven = findViewById(R.id.Package)
        link = findViewById(R.id.LinktoApply)
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

        if(cmpname.isEmpty()){
            companyName.error="Please enter company name"
        }
        if(cmprole.isEmpty()){
            companyName.error="Please enter role offered"
        }
        if(cmppackage.isEmpty()){
            companyName.error="Please enter package offered"
        }
        if(cmplink.isEmpty()){
            companyName.error="Please enter company registration link"
        }

        val cmpId = dbRef.push().key!!

        val company = CompanyModel(cmpId,cmpname,cmprole,cmppackage,cmplink)

        dbRef.child(cmpId).setValue(company).addOnCompleteListener{
            Toast.makeText(this,"Data inserted successfully",Toast.LENGTH_LONG).show()

            companyName.text.clear()
            role.text.clear()
            packageGiven.text.clear()
            link.text.clear()

        }.addOnFailureListener{err->
            Toast.makeText(this,"Error ${err.message}",Toast.LENGTH_LONG).show()
        }
    }

    fun homeClicked(view: View){
        val intent = Intent(baseContext, TPOHome::class.java)
        intent.putExtra("userEmail", userEmail)
        startActivity(intent)
        finish()
    }

}