package com.example.bmsce

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bmsce.databinding.TpoRegisterBinding
import com.example.bmsce.models.UserData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TPORegister : AppCompatActivity()  {
    private lateinit var binding: TpoRegisterBinding
    private lateinit var firebaseDatabase : FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TpoRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")

        binding.tpoRegister.setOnClickListener {
            val signupEmail = binding.signupEmailTpo.text.toString()
            val signupPassword = binding.signupPasswordTpo.text.toString()

            if(signupEmail.isNotEmpty() && signupPassword.isNotEmpty()){
                if (signupEmail.endsWith(".ise@bmsce.ac.in"))
                {
                    signupUser(signupEmail,signupPassword)
                }
                else
                {
                    Toast.makeText(this@TPORegister, "Please use a valid '.ise@bmsce.ac.in' email",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this@TPORegister, "All fields are mandatory",Toast.LENGTH_SHORT).show()
            }
        }

        binding.tpoLoginRedirect.setOnClickListener{
            startActivity(Intent(this@TPORegister, TPOLogin::class.java))
            finish()
        }

    }

    private fun signupUser(email: String, password: String) {
        // Check if the email ends with ".ise@bmsce.ac.in"
        if (email.endsWith(".ise@bmsce.ac.in")) {
            databaseReference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (!dataSnapshot.exists()) {
                        val id = databaseReference.push().key
                        val userData = UserData(id, email, password)
                        databaseReference.child(id!!).setValue(userData)

                        Toast.makeText(this@TPORegister, "Signup Successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@TPORegister, TPOLogin::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@TPORegister, "User already exists", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@TPORegister, "Database Error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(this@TPORegister, "Only .ise@bmsce.ac.in emails are allowed to register", Toast.LENGTH_SHORT).show()
        }
    }
}