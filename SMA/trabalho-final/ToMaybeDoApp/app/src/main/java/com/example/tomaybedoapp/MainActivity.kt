package com.example.tomaybedoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "ToMaybeDoApp";

        //NotesActivity Activity Button
        val notesBoardButton:Button = findViewById(R.id.btnOpenNotesBoard);
        notesBoardButton.setOnClickListener {
            startActivity(Intent(this, NotesActivity::class.java))
        }

        //DiagramCreator Activity Button
        val diagramCreatorButton:Button = findViewById(R.id.btnOpenDiagramCreator);
        diagramCreatorButton.setOnClickListener {
            startActivity(Intent(this, DiagramCreator::class.java))
        }

    }
}