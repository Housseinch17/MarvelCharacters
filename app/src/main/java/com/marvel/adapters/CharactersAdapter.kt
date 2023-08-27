package com.marvel.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marvel.R
import com.marvel.beans.Results
import com.marvel.databinding.ItemCharacterBinding
import com.marvel.interfaces.OnItemClickListener


class CharactersAdapter(private var DataArrayList: ArrayList<Results>,val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<CharactersAdapter.MyViewHolder>() {

    fun setData(newData: ArrayList<Results>) {
        DataArrayList = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemCharacterBinding = ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding,onItemClickListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = DataArrayList[position]
        holder.name.text = item.name
        holder.description.text = item.description

        if (item.thumbnail != null && item.thumbnail.path != null
            && item.thumbnail.extension != null)
        {
            val path: String = item.thumbnail.path
            val extension: String = item.thumbnail.extension
            Glide.with(holder.itemView.context)
                .load("$path.$extension")
                .placeholder(R.drawable.marvel)
                .into(holder.image)
        }
        else
            holder.image.setImageResource(R.drawable.marvel)

        holder.itemView.setOnClickListener {
            val item = DataArrayList[position]
            onItemClickListener.navigateToCharacterDetailsFragment(item)
        }

    }

    override fun getItemCount(): Int {
        return DataArrayList.size
    }

    inner class MyViewHolder(
        itemList: ItemCharacterBinding,
        onItemClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(itemList.root) {
        var name: TextView
        var description: TextView
        var image: ImageView
        var ivArrow :ImageView


        init {
            name = itemList.txName
            description = itemList.txDescription
            image = itemList.characterImg
            ivArrow = itemList.ivArrow
        }
    }
}