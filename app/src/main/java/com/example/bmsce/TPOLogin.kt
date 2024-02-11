package com.example.bmsce

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bmsce.databinding.TpoLoginBinding
import com.example.bmsce.models.UserData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class TPOLogin : AppCompatActivity() {

    private lateinit var binding: TpoLoginBinding
    private lateinit var firebaseDatabase : FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TpoLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")

        binding.tpologin.setOnClickListener{
            val loginEmail = binding.loginEmailTpo.text.toString()
            val loginPassword = binding.loginPasswordTpo.text.toString()
            if(loginEmail.isNotEmpty() && loginPassword.isNotEmpty()){
                loginUser(loginEmail,loginPassword)
            }else{
                Toast.makeText(this@TPOLogin, "All fields are mandatory",Toast.LENGTH_SHORT).show()
            }
        }

        binding.newuserTpo.setOnClickListener{
            startActivity(Intent(this@TPOLogin, TPORegister::class.java))
            finish()
        }

    }

    private fun loginUser(email : String, password: String){
        databaseReference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(object :
            ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()){
                    for(userSnapshot in dataSnapshot.children){
                        val userData = userSnapshot.getValue(UserData::class.java)


                        if(userData != null && userData.password == password){
                            Toast.makeText(this@TPOLogin, "Login Successful", Toast.LENGTH_SHORT).show()

                            val welcomeMessage = "Welcome, ${userData.email}"
                            Toast.makeText(this@TPOLogin, welcomeMessage, Toast.LENGTH_SHORT).show()

                            // Put the "userEmail" extra into the intent
                            val intent = Intent(this@TPOLogin, TPOHome::class.java)
                            intent.putExtra("userEmail", email)

                            // Start the TPOHome activity with the prepared intent
                            startActivity(intent)

                            // Finish the current activity
                            finish()
                            return
                        }
                    }
                }
                    Toast.makeText(this@TPOLogin, "Login Failed",Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@TPOLogin, "Database Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}