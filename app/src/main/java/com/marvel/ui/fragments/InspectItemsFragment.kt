package com.marvel.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.marvel.R
import com.marvel.beans.Results
import com.marvel.databinding.FragmentInspectItemsBinding
import com.marvel.ui.BaseFragment

class InspectItemsFragment : BaseFragment() {
  private lateinit var binding:FragmentInspectItemsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentInspectItemsBinding.inflate(layoutInflater)

        binding.toolbarDetails.toolbarTitle.text = "${R.string.inspect_items}"
        binding.toolbarDetails.backBtn.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        setupViews()
        return binding.root
    }
    override fun setupViews() {
        val bundle = requireArguments()
        if (bundle != null) {
            val details = bundle.getParcelable<Results>("details")
            if (details != null && details.thumbnail != null && details.thumbnail.path != null
                && details.thumbnail.extension != null
            ) {
                val path: String = details.thumbnail.path
                val extension: String = details.thumbnail.extension
                Glide.with(this)
                    .load("$path.$extension")
                    .placeholder(R.drawable.marvel)
                    .into(binding.imgView)
            } else
                binding.imgView.setImageResource(R.drawable.marvel)
            binding.txTitle.text = details?.title
            binding.txDescription.text = details?.description

        }

    }
}