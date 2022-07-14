package com.tvl.tvltodolist.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.tvl.tvltodolist.R
import com.tvl.tvltodolist.databinding.FragmentNotesListBinding
import com.tvl.tvltodolist.model.Notes
import com.tvl.tvltodolist.ui.activities.MainActivity
import com.tvl.tvltodolist.ui.adapters.NotesAdapter
import com.tvl.tvltodolist.viewmodel.NotesViewModel

class NotesListFragment : Fragment() {

    lateinit var bind: FragmentNotesListBinding
    lateinit var viewModel: NotesViewModel
    lateinit var adapter: NotesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = (activity as MainActivity).viewModel
        // Inflate the layout for this fragment
        bind = DataBindingUtil.inflate(
            inflater, R.layout.fragment_notes_list, container, false
        )
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = NotesAdapter(this::clickAction)
        actions()
    }

    private fun clickAction(note: Notes) {
        viewModel.valuePassForUpdate(note)
        findNavController().navigate(R.id.action_notesListFragment_to_updateNotesFragment)
    }

    private fun actions() {
        viewModel.allNotes.observe(viewLifecycleOwner, Observer { notes ->
            adapter.setList(notes = notes)
            bind.rclViewNotes.layoutManager = GridLayoutManager(activity, 2)
            bind.rclViewNotes.adapter = adapter
            adapter.notifyDataSetChanged()
        })
        bind.btnAddNotes.setOnClickListener {
            findNavController().navigate(
                R.id.action_notesListFragment_to_insertNoteFragment
            )
        }

    }


}