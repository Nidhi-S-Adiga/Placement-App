package com.example.bmsce

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bmsce.databinding.TpoHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TPOHome : AppCompatActivity(){
    private lateinit var binding: TpoHomeBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TpoHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        // Set click listener for the logout button
        binding.logoutbtn1.setOnClickListener {
            // Sign out the current user
            firebaseAuth.signOut()

            Toast.makeText(this, "You have been logged out", Toast.LENGTH_SHORT).show()

            // Start the InitialHomeScreenActivity and finish the current activity
            startActivity(Intent(this@TPOHome, MainActivity::class.java))
            finish()
        }
    }

}