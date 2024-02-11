package com.example.bmsce

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class StudentHome : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var userEmailTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.student_home)

        firebaseAuth = FirebaseAuth.getInstance()

        userEmailTextView = findViewById(R.id.userEmailTextView)

        // Display logged in user's email
        displayUserEmail()
    }

    private fun displayUserEmail() {
        // Check if user is logged in
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            // User is logged in, retrieve and display their email
            val userEmail = currentUser.email
            Toast.makeText(this, "Welcome,$userEmail", Toast.LENGTH_SHORT).show()
            userEmailTextView.text = "$userEmail"
        } else {
            // User is not logged in, handle accordingly
            userEmailTextView.text = "Not logged in"
        }
    }

    fun LogoutStudent(view: View) {
        // Sign out user from Firebase
        firebaseAuth.signOut()

        Toast.makeText(this, "You have been logged out", Toast.LENGTH_SHORT).show()

        // Redirect to login or home activity
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Optional: Close current activity to prevent user from coming back to it using back button
    }

    fun viewCompany(view: View){
        val intent = Intent(baseContext, FetchingActivity::class.java)
        startActivity(intent)
        finish()
    }
}