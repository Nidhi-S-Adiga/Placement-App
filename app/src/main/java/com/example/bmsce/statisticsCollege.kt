package com.example.bmsce

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class statisticsCollege : AppCompatActivity(){
    private var userEmail: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.statistics)
        val butt1 = findViewById(R.id.statistics_button1) as Button
        val butt2 = findViewById(R.id.statistics_button2) as Button
        val butt3 = findViewById(R.id.statistics_button3) as Button
        val butt4 = findViewById(R.id.statistics_button4) as Button


        userEmail = intent.getStringExtra("userEmail")
        if (userEmail == null) {
            userEmail = "No message found"
        }

        butt1.setOnClickListener{

            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setMessage("Total Internships \t: \t270\n" +
                    "Students Placed   : \t635\n" +
                    "Total Offers  \t\t    : 1078\n" +
                    "Total Percentage  : 79")
            alertDialogBuilder.setCancelable(true)
            alertDialogBuilder.setPositiveButton("Close") {dialog,_-> dialog.dismiss()
        }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
    }
        butt2.setOnClickListener{

            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setMessage("Total Internships \t: \t242\n" +
                    "Students Placed   : \t571\n" +
                    "Total Offers  \t\t    : 1047\n" +
                    "Total Percentage  : 68")
            alertDialogBuilder.setCancelable(true)
            alertDialogBuilder.setPositiveButton("Close") {dialog,_-> dialog.dismiss()
            }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
        butt3.setOnClickListener{

            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setMessage("Total Internships \t: \t553\n" +
                    "Students Placed   : \t792\n" +
                    "Total Offers    \t\t  : 1185\n" +
                    "Total Percentage  : 71")
            alertDialogBuilder.setCancelable(true)
            alertDialogBuilder.setPositiveButton("Close") {dialog,_-> dialog.dismiss()
            }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
        butt4.setOnClickListener{

            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setMessage("Total Internships \t: \t556\n" +
                    "Students Placed   : \t697\n" +
                    "Total Offers   \t\t   : 1192\n" +
                    "Total Percentage  : 72")
            alertDialogBuilder.setCancelable(true)
            alertDialogBuilder.setPositiveButton("Close") {dialog,_-> dialog.dismiss()
            }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
        }

    fun goBack(view: View?) {
        val userType = intent.getStringExtra("userType")
        val intent: Intent
        if ("Student" == userType) {
            intent = Intent(this, StudentHome::class.java)
        } else if ("TPO" == userType) {
            intent = Intent(this, TPOHome::class.java)
            intent.putExtra("userEmail", userEmail)
        } else {
            throw IllegalArgumentException("Invalid user type")
        }
        startActivity(intent)
        finish()
    }
    }


