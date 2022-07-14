package com.tvl.tvltodolist.ui.fragments

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.tvl.tvltodolist.R
import com.tvl.tvltodolist.databinding.FragmentInsertNoteBinding
import com.tvl.tvltodolist.databinding.FragmentUpdateNotesBinding
import com.tvl.tvltodolist.model.Notes
import com.tvl.tvltodolist.ui.activities.MainActivity
import com.tvl.tvltodolist.viewmodel.NotesViewModel
import java.text.SimpleDateFormat
import java.util.*


class UpdateNotesFragment : Fragment() {
    lateinit var bind: FragmentUpdateNotesBinding
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
        // Inflate the layout for this fragment
        viewModel = (activity as MainActivity).viewModel
        bind = DataBindingUtil.inflate(
            inflater, R.layout.fragment_update_notes, container, false
        )
        return bind.root
    }

    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.apply {
            editTextTitleUpdate.text = viewModel.updateNote.title.toEditable()
            editTextSubTitleUpdate.text = viewModel.updateNote.subtitle.toEditable()
            editTextNotesDataUpdate.text = viewModel.updateNote.notes.toEditable()
            if (viewModel.updateNote.notesPriority.contains("1")) {
                greenPriorityUpdate.setImageResource(R.drawable.ic_baseline_done_24)
                yellowPriorityUpdate.setImageResource(0)
                redPriorityUpdate.setImageResource(0)
            }
            if (viewModel.updateNote.notesPriority.contains("3")) {
                greenPriorityUpdate.setImageResource(0)
                yellowPriorityUpdate.setImageResource(0)
                redPriorityUpdate.setImageResource(R.drawable.ic_baseline_done_24)
            }
            if (viewModel.updateNote.notesPriority.contains("2")) {
                greenPriorityUpdate.setImageResource(0)
                yellowPriorityUpdate.setImageResource(R.drawable.ic_baseline_done_24)
                redPriorityUpdate.setImageResource(0)
            }



            greenPriorityUpdate.setOnClickListener {
                priority = "1"
                greenPriorityUpdate.setImageResource(R.drawable.ic_baseline_done_24)
                yellowPriorityUpdate.setImageResource(0)
                redPriorityUpdate.setImageResource(0)
            }
            redPriorityUpdate.setOnClickListener {
                priority = "3"
                greenPriorityUpdate.setImageResource(0)
                yellowPriorityUpdate.setImageResource(0)
                redPriorityUpdate.setImageResource(R.drawable.ic_baseline_done_24)

            }
            yellowPriorityUpdate.setOnClickListener {
                priority = "2"
                greenPriorityUpdate.setImageResource(0)
                yellowPriorityUpdate.setImageResource(R.drawable.ic_baseline_done_24)
                redPriorityUpdate.setImageResource(0)
            }

            btnDoneNotesUpdate.setOnClickListener {
                updateNotes()
                findNavController().navigate(R.id.action_updateNotesFragment_to_notesListFragment)
            }

            btnDeleteNotesUpdate.setOnClickListener {

                val ad = AlertDialog.Builder(activity!!)
                ad.setTitle("Alert!")
                ad.setMessage("Do you want to delete this note")
                ad.setIcon(R.drawable.ic_baseline_add_alert_24)
                ad.setPositiveButton("Yes") { dialog, which ->
                    deleteNotes()
                    findNavController().navigate(R.id.action_updateNotesFragment_to_notesListFragment)
                }
                ad.setNegativeButton("No") { dialog, which ->
                    dialog.dismiss()
                }
                ad.setCancelable(false)
                ad.show()

            }
        }

    }

    private fun deleteNotes() {
        viewModel.deleteNotes(
            Notes(
                id = viewModel.updateNote.id,
                title = bind.editTextTitleUpdate.text.toString(),
                subtitle = bind.editTextSubTitleUpdate.text.toString(),
                notes = bind.editTextNotesDataUpdate.text.toString(),
                notesPriority = priority,
                notesDate = SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Date()).toString()
            )

        )
        Toast.makeText(activity, "Notes Deleted Successfully", Toast.LENGTH_LONG)
            .show()
    }

    private fun updateNotes() {
        viewModel.updateNotes(
            Notes(
                id = viewModel.updateNote.id,
                title = bind.editTextTitleUpdate.text.toString(),
                subtitle = bind.editTextSubTitleUpdate.text.toString(),
                notes = bind.editTextNotesDataUpdate.text.toString(),
                notesPriority = priority,
                notesDate = Calendar.getInstance().time.toString()
            )
        )

        Toast.makeText(activity, "Notes Updated Successfully", Toast.LENGTH_LONG)
            .show()
    }
}