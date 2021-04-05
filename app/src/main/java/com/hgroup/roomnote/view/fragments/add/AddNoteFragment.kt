package com.hgroup.roomnote.view.fragments.add

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hgroup.roomnote.R
import com.hgroup.roomnote.databinding.FragmentAddNoteBinding
import com.hgroup.roomnote.model.Note
import com.hgroup.roomnote.viewmodel.NoteViewModel

class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding
    private lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddNoteBinding.inflate(layoutInflater)
        // get reference of viewModel
        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)


        setOnclickers()






        return binding.root
    }

    private fun checkFields(field1: String, field2: String): Boolean {
        return (field1.length > 1 && field2.length > 1)

    }

    private fun setOnclickers() {
        binding.apply {
            //checks valid title and content then sends to viewModel
            btnSave.setOnClickListener {
                val title = binding.etTitle.text.toString()
                val content = binding.etContent.text.toString()
                if (checkFields(title, content)) {
                    addNote(Note(0, title, content))
                    showToast(true)
                    navigateHome()
                    binding.root.hideKeyboard()

                } else {
                    showToast(false)
                }
            }
            btnCancel.setOnClickListener {
                binding.root.hideKeyboard()
                navigateHome()
            }
        }

    }

    private fun addNote(note: Note) {
        viewModel.addNote(note)

    }

    private fun showToast(isOkay: Boolean) {
        when (isOkay) {
            true -> {
                Toast.makeText(this.activity, "NOTE ADDED", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(this.activity, "FIELDS CAN'T BE EMPTY", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun navigateHome() {
        findNavController().navigate(R.id.action_addNoteFragment_to_homeFragment)

    }

    //hiding keyboard after data changes
    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }


}