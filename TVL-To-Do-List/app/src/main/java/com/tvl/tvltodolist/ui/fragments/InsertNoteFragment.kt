package com.tvl.tvltodolist.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.tvl.tvltodolist.R
import com.tvl.tvltodolist.databinding.FragmentInsertNoteBinding
import com.tvl.tvltodolist.databinding.FragmentNotesListBinding
import com.tvl.tvltodolist.model.Notes
import com.tvl.tvltodolist.ui.activities.MainActivity
import com.tvl.tvltodolist.ui.adapters.NotesAdapter
import com.tvl.tvltodolist.viewmodel.NotesViewModel
import java.text.SimpleDateFormat
import java.util.*


class InsertNoteFragment : Fragment() {
    lateinit var bind: FragmentInsertNoteBinding
    lateinit var viewModel: NotesViewModel
    private lateinit var title: String
    private lateinit var subTitle: String
    private lateinit var notes: String
    private var priority: String = "1"
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
            inflater, R.layout.fragment_insert_note, container, false
        )
        return bind.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.apply {

            btnDoneNotes.setOnClickListener {
                title = editTextTitle.text.toString()
                subTitle = editTextSubTitle.text.toString()
                notes = editTextNotesData.text.toString()
                createNotes(title, subTitle, notes)
                findNavController().navigate(
                    R.id.action_insertNoteFragment_to_notesListFragment
                )
            }
            greenPriority.setOnClickListener {
                priority = "1"
                greenPriority.setImageResource(R.drawable.ic_baseline_done_24)
                yellowPriority.setImageResource(0)
                redPriority.setImageResource(0)
            }
            redPriority.setOnClickListener {
                priority = "3"
                greenPriority.setImageResource(0)
                yellowPriority.setImageResource(0)
                redPriority.setImageResource(R.drawable.ic_baseline_done_24)

            }
            yellowPriority.setOnClickListener {
                priority = "2"
                greenPriority.setImageResource(0)
                yellowPriority.setImageResource(R.drawable.ic_baseline_done_24)
                redPriority.setImageResource(0)
            }

        }
    }

    private fun createNotes(title: String, subTitle: String, notes: String) {

        viewModel.insertNotes(
            Notes(
                id = 0,
                title = title,
                subtitle = subTitle,
                notes = notes,
                notesPriority = priority,
                notesDate = SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Date()).toString()
            )
        )

        Toast.makeText(activity, "Notes Created Successfully", Toast.LENGTH_LONG)
            .show()

    }

}