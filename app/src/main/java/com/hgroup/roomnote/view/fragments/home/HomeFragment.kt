package com.hgroup.roomnote.view.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hgroup.roomnote.R
import com.hgroup.roomnote.databinding.FragmentHomeBinding
import com.hgroup.roomnote.viewmodel.NoteViewModel


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHomeBinding.inflate(layoutInflater)
        val viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        //recyclerview settings
        //reference a recycler
        val recyclerView = binding.recyclerView
        //set an adapter
        val adapter = ListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //observes always for noteList for liveData changes
        viewModel.readAllNote.observe(viewLifecycleOwner, Observer { noteList ->
            adapter.setData(noteList)

        })


        //fab nav -> to add note
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)
        }






        return binding.root

    }


}