package com.hgroup.roomnote.view.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.hgroup.roomnote.databinding.RowLayoutBinding
import com.hgroup.roomnote.model.Note

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var noteList = emptyList<Note>()


    class MyViewHolder(val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

            //LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = noteList[position]
        holder.binding.apply {
            tvId.text = currentItem.id.toString()
            tvTitle.text = currentItem.title
            tvContent.text = currentItem.content
        }
        holder.binding.rowItem.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)

        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }


    fun setData(noteList: List<Note>) {
        this.noteList = noteList
        notifyDataSetChanged()
    }


}