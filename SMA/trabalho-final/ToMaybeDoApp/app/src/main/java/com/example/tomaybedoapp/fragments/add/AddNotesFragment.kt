package com.example.tomaybedoapp.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tomaybedoapp.R
import com.example.tomaybedoapp.models.Note
import com.example.tomaybedoapp.viewmodels.NoteViewModel
import kotlinx.android.synthetic.main.fragment_add_notes.*
import kotlinx.android.synthetic.main.fragment_add_notes.view.*

class AddNotesFragment : Fragment(){

    private lateinit var myNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_notes, container, false)
        myNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        view.addNoteButton.setOnClickListener {
            insertNoteToDataBase()
        }

        return view
    }

    //
    // Function to insert the Note inside notes_table
    //
    private fun insertNoteToDataBase() {
        val noteTitle = addNotesTitle_et.text.toString()
        val noteDesc = addNotesDescription_et.text.toString()

        if (checkInput(noteTitle, noteDesc)) {
            //Create Note Object
            val note = Note(0, noteTitle, noteDesc)

            //Insert to DB
            myNoteViewModel.addNote(note)

            //Visual message to know the note has been successfuly inserted
            Toast.makeText(requireContext(), "Successfully Added!", Toast.LENGTH_LONG).show()

            //Return to the view ListNotesFragment
            findNavController().navigate(R.id.action_addNotes_to_notesActivity)

        } else {
            //Warning message
            //All fields need to be filled
            Toast.makeText(requireContext(), "Fill out all fields!", Toast.LENGTH_LONG).show()
        }
    }



    //Check if input data for title and description are null or not
    //Return true if all fields are filled correctly
    private fun checkInput(title: String, description: String): Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))
    }
}