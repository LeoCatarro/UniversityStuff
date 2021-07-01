package com.example.tomaybedoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DiagramCreator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagram_creator)

        title="ToMaybeDoApp";

        //Main Buttons OnClickListener events
        val button: Button = findViewById(R.id.btnOpenMain);
        button.setOnClickListener {
            val intent = Intent(this@DiagramCreator, MainActivity::class.java)
            startActivity(intent)
        }
    }
}