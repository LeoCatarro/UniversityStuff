package com.example.tomaybedoapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tomaybedoapp.R
import com.example.tomaybedoapp.models.Note
import com.example.tomaybedoapp.viewmodels.NoteViewModel
import kotlinx.android.synthetic.main.fragment_update_notes.*
import kotlinx.android.synthetic.main.fragment_update_notes.view.*

class UpdateNotesFragment : Fragment() {

    //Used safe args to when user clicks in a note will be redirected to the update fragment with all fields filled ready to be updated and safe the current note ID,
    // to update the correct one
    private val args by navArgs<UpdateNotesFragmentArgs>()
    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_update_notes, container, false)

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        view.updateNotesTitle_et.setText(args.currentNote.title)
        view.updateNotesDescription_et.setText(args.currentNote.description)

        view.updateNoteButton.setOnClickListener {
            updateNote()
        }

        //Add delete menu
        setHasOptionsMenu(true)

        return view
    }

    private fun updateNote(){
        val title = updateNotesTitle_et.text.toString()
        val description = updateNotesDescription_et.text.toString()

        if(checkInput(title, description))
        {
            //Create updated note
            val updatedNote = Note(args.currentNote.id, title, description)
            //Update into DB
            mNoteViewModel.updateNote(updatedNote)
            //Successfully Updated Note Message
            Toast.makeText(requireContext(), "Successfuly Updated!", Toast.LENGTH_SHORT).show()
            //Navigate back to ListNotes Fragment
            findNavController().navigate(R.id.action_updateNotesFragment_to_notesActivity)
        }

        else
        {
            Toast.makeText(requireContext(), "Fill all Fields, please!", Toast.LENGTH_SHORT).show()
        }
    }

    //Check if input data for title and description are null or not
    //Return true if all fields are filled correctly
    private fun checkInput(title: String, description: String): Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.delete_note_menu){
            deleteNote()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteNote() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_, _ ->
            //If pressed "Yes" lets remove the Note from DB!
            mNoteViewModel.deleteNote(args.currentNote)
            //Successfuly removed note message
            Toast.makeText(requireContext(), "Successfuly Removed ${args.currentNote.title}", Toast.LENGTH_SHORT).show()
            //Navigate back to ListNotes Fragment
            findNavController().navigate(R.id.action_updateNotesFragment_to_notesActivity)
        }
        builder.setNegativeButton("No") {_, _ -> }
        builder.setTitle("Delete ${args.currentNote.title}?")
        builder.setMessage("Are you about to delete ${args.currentNote.title}?")
        builder.create().show()
    }
}