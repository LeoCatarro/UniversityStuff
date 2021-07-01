package com.example.tomaybedoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class NotesBoard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_board)

        title="ToMaybeDoApp";

        //Main Buttons OnClickListener events
        val button: Button = findViewById(R.id.btnOpenMain);
        button.setOnClickListener {
            val intent = Intent(this@NotesBoard, MainActivity::class.java)
            startActivity(intent)
        }
    }
}