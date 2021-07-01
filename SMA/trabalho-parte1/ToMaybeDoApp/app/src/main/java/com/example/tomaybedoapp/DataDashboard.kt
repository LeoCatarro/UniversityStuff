package com.example.tomaybedoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DataDashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_dashboard)

        title="ToMaybeDoApp";

        //Main Buttons OnClickListener events
        val button: Button = findViewById(R.id.btnOpenMain);
        button.setOnClickListener {
            val intent = Intent(this@DataDashboard, MainActivity::class.java)
            startActivity(intent)
        }
    }
}