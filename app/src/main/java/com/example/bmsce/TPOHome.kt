package com.example.bmsce

import android.content.Intent
import android.health.connect.datatypes.units.Length
import android.os.Bundle
import android.view.View
import android.widget.TextView
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
    private lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TpoHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        val userEmail = intent.extras?.getString("userEmail")?:"No message found"
        email=intent.extras?.getString("userEmail")?:"No message found"

        // Find the TextView by its ID in the XML layout
        val userEmailTextView: TextView = findViewById(R.id.TpoEmailTextView)

        userEmailTextView.text = userEmail

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

    fun addCompanyClicked(view: View){
        val intent = Intent(baseContext, AddCompany::class.java)
        intent.putExtra("userEmail", email)
        intent.putExtra("userType", "TPO")
        startActivity(intent)
        finish()
    }

    fun onAboutClick(view: View) {
        val intent = Intent(this, aboutCollege::class.java)
        startActivity(intent)
        intent.putExtra("userType", "TPO")
        intent.putExtra("userEmail", email)
        startActivity(intent)
        finish()
    }

    fun aboutStatistics(view: View) {
        val intent = Intent(this,statisticsCollege::class.java)
        startActivity(intent)
        intent.putExtra("userType", "TPO")
        intent.putExtra("userEmail", email)
        startActivity(intent)
        finish()
    }

    fun aboutPlacement(view: View) {
        val intent = Intent(this,placementDetails::class.java)
        startActivity(intent)
        intent.putExtra("userType", "TPO")
        intent.putExtra("userEmail", email)
        startActivity(intent)
        finish()
    }
}
