package com.marvel.adapters.all

import AllCategories
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marvel.adapters.single.CategoryAdapter
import com.marvel.databinding.ItemListCategoryBinding
import com.marvel.interfaces.ItemListener


class AllCategoriesAdapter (var dataArrayList: ArrayList<AllCategories>,val itemListener: ItemListener) :
    RecyclerView.Adapter<AllCategoriesAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemListCategoryBinding = ItemListCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = dataArrayList[position]
        holder.txTitle.text = item.title
        holder.allCategoriesRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context,
        LinearLayoutManager.HORIZONTAL,false)

        val categoryAdapter = CategoryAdapter(item.categories)
        holder.allCategoriesRecyclerView.adapter=categoryAdapter

        holder.viewAll.setOnClickListener{
            val bundle = Bundle()
            bundle.putParcelable("myObjects", item)
            itemListener.navigateToDetailsFragment(bundle)
        }
    }

    override fun getItemCount(): Int {
        return dataArrayList.size
    }

    fun updateList(newCategories: ArrayList<AllCategories>){
        val oldCategories=dataArrayList
        val diffResult:DiffUtil.DiffResult=DiffUtil.calculateDiff(
            BriefDiffCallBack(
                oldCategories,
                newCategories
            )
        )
        dataArrayList=newCategories
        diffResult.dispatchUpdatesTo(this)
    }
    class BriefDiffCallBack(
        var oldCategories:ArrayList<AllCategories>,
        var newCategories:ArrayList<AllCategories>)
        : DiffUtil.Callback(){
        override fun getOldListSize(): Int {
            return oldCategories.size
        }

        override fun getNewListSize(): Int {
            return newCategories.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldCategories.get(oldItemPosition).title == newCategories.get(newItemPosition).title)
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldCategories.get(oldItemPosition).equals(newCategories.get(newItemPosition)))
        }
    }

    class MyViewHolder(itemList: ItemListCategoryBinding) : RecyclerView.ViewHolder(itemList.root) {
        var allCategoriesRecyclerView:RecyclerView
        var txTitle:TextView
        var viewAll:TextView
        init {
            allCategoriesRecyclerView = itemList.allCategoriesListview
            txTitle = itemList.txTitle
            viewAll=itemList.viewAll
        }
    }
}