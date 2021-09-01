package com.example.tomaybedoapp


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_diagram_creator.*


class DiagramCreator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#666666")))     //Change ActionBar background color
        setContentView(R.layout.activity_diagram_creator)
        title="DiagramCreator";

        ControlUndo.setOnClickListener {
            PaintArea.setUndo()
        }
        ControlRedo.setOnClickListener {
            PaintArea.setRedo()
        }
        ClearAllPaths.setOnClickListener {
            PaintArea.setDeleteAll()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}