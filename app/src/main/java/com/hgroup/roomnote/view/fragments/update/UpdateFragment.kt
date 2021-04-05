package com.hgroup.roomnote.view.fragments.update

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hgroup.roomnote.R
import com.hgroup.roomnote.databinding.FragmentUpdateBinding
import com.hgroup.roomnote.model.Note
import com.hgroup.roomnote.viewmodel.NoteViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var viewModel: NoteViewModel
    private lateinit var binding: FragmentUpdateBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        binding = FragmentUpdateBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        //creates a menu button in actionbar
        setHasOptionsMenu(true)

        //get args from last fragment which is clicked
        getHomeArgs()


        setOnclickers()








        return binding.root
    }

    private fun getHomeArgs() {

        binding.apply {
            etUpdateTitle.setText(args.currentNote.title)
            etUpdateContent.setText(args.currentNote.content)
        }
    }

    private fun setOnclickers() {
        binding.apply {

            btnSaveUpdate.setOnClickListener {
                val title = binding.etUpdateTitle.text.toString()
                val content = binding.etUpdateContent.text.toString()
                if (checkFields(title, content)) {
                    updateNote(Note(args.currentNote.id, title, content))
                    showToast(true)
                    navigateHome()
                    binding.root.hideKeyboard()


                } else {
                    showToast(false)

                }

            }
            btnCancelUpdate.setOnClickListener {
                navigateHome()
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteNote()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteNote() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("OK") { _, _ ->
            Toast.makeText(
                requireContext(),
                "${args.currentNote.title} IS DELETED",
                Toast.LENGTH_SHORT
            ).show()
            viewModel.deleteNote(args.currentNote)
            navigateHome()
        }
        builder.setNegativeButton("CANCEL") { _, _ ->
        }
        builder.setTitle("DELETE ${args.currentNote.title}?")
        builder.setMessage("Are you sure you want to delete ${args.currentNote.content.take(20)}?")
        builder.create().show()
    }

    private fun updateNote(note: Note) {
        viewModel.updateNote(note)
    }

    private fun checkFields(field1: String, field2: String): Boolean {
        return (field1.length > 1 && field2.length > 1)

    }

    private fun showToast(isOkay: Boolean) {
        when (isOkay) {
            true -> {
                Toast.makeText(this.activity, "NOTE UPDATED", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(this.activity, "FIELDS CAN'T BE EMPTY", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun navigateHome() {
        findNavController().navigate(R.id.action_updateFragment_to_homeFragment)

    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }


}