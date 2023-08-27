package com.marvel.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marvel.R
import com.marvel.beans.Results
import com.marvel.databinding.ItemDetailsBinding
import com.marvel.interfaces.InspectItemsListener

class DetailsAdapter(var detailsArray: ArrayList<Results>, private val inspectListener: InspectItemsListener) :
    RecyclerView.Adapter<DetailsAdapter.MyViewHolder>() {


    class MyViewHolder(itemList: ItemDetailsBinding) : RecyclerView.ViewHolder(itemList.root) {
        var title: TextView
        var description: TextView
        var image: ImageView
        init {
            title = itemList.txTitle
            description = itemList.txDescription
            image = itemList.detailsImg
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemDetailsBinding = ItemDetailsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = detailsArray[position]
        holder.title.text = item.title
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
            val bundle = Bundle()
            bundle.putParcelable("details", item)
            inspectListener.navigateToInspectItemsFragment(bundle)
        }
    }

    override fun getItemCount(): Int {
        return detailsArray.size
    }
}