package com.example.bmsce

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bmsce.databinding.TpoHomeBinding
import com.google.firebase.auth.FirebaseAuth

class TPOHome : AppCompatActivity(){
    private lateinit var binding: TpoHomeBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TpoHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        // Set click listener for the logout button
        binding.logoutbtn.setOnClickListener {
            // Sign out the current user
            firebaseAuth.signOut()

            // Start the InitialHomeScreenActivity and finish the current activity
            startActivity(Intent(this@TPOHome, MainActivity::class.java))
            finish()
        }
    }

}