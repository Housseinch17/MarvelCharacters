package com.marvel.ui.fragments

import AllCategories
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.marvel.R
import com.marvel.adapters.DetailsAdapter
import com.marvel.databinding.FragmentDetailsBinding
import com.marvel.interfaces.InspectItemsListener
import com.marvel.ui.BaseFragment

class DetailsFragment : BaseFragment(),InspectItemsListener {
    private lateinit var binding:FragmentDetailsBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentDetailsBinding.inflate(layoutInflater)


        binding.detailsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.toolbarDetails.backBtn.setOnClickListener{
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        setupViews()

        return binding.root
    }
    override fun setupViews() {
        binding.toolbarDetails.toolbarTitle.text= R.string.details.toString()
        val bundle = requireArguments()
        if (bundle != null) {
            val myObject = bundle.getParcelable<AllCategories>("myObjects")
            val arrayList = myObject?.categories
            val detailsAdapter = arrayList?.let { DetailsAdapter(it,this) }
            binding.detailsRecyclerView.adapter = detailsAdapter
            binding.txTitle.text = myObject?.title
        }
    }

    override fun navigateToInspectItemsFragment(bundle: Bundle) {
        val navController = Navigation.findNavController(binding.root)
        navController.navigate(R.id.action_detailsFragment_to_inspectItemsFragment,bundle)
    }
}