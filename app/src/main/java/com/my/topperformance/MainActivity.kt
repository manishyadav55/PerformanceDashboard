package com.my.topperformance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var HealthWorkerlayout: TextView
    lateinit var Doctorlayout: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Home Page"
        HealthWorkerlayout=findViewById(R.id.txtHealthWorkerActivity)
        Doctorlayout=findViewById(R.id.txtDoctorActivity)

        Doctorlayout.setOnClickListener {
            val intent = Intent(this, DoctorActivity::class.java).apply {
            }
            startActivity(intent)

        }
        HealthWorkerlayout.setOnClickListener {
            val intent = Intent(this, NurseActivity::class.java).apply {
            }
            startActivity(intent)
        }


    }
}