package com.example.tomaybedoapp.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tomaybedoapp.R
import com.example.tomaybedoapp.viewmodels.NoteViewModel
import kotlinx.android.synthetic.main.fragment_list_notes.view.*


class ListNotesFragment : Fragment() {

    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_list_notes, container, false)

        //Recycler View
        val adapter = ListNoteAdapter()
        val recyclerView = view.recyclerNotesView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        //NoteViewModel
        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        mNoteViewModel.listAllNotes.observe(viewLifecycleOwner, Observer{ note ->
            adapter.setData(note)
        })

        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listNotes_to_addNotes)
        }

        //Add delete menu
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.delete_note_menu){
            deleteAllNotes()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllNotes() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_, _ ->
            //If pressed "Yes" lets remove the Note from DB!
            mNoteViewModel.deleteAllNotes()
            //Successfuly removed note message
            Toast.makeText(requireContext(), "All Notes Successfully Removed!", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") {_, _ -> }
        builder.setTitle("Delete All?")
        builder.setMessage("Are you about to delete all notes?")
        builder.create().show()
    }
}