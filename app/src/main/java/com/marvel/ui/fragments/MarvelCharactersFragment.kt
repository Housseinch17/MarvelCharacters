package com.marvel.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.marvel.R
import com.marvel.adapters.CharactersAdapter
import com.marvel.beans.Results
import com.marvel.config.Constants
import com.marvel.databinding.FragmentMarvelCharactersBinding
import com.marvel.viewmodel.CharactersViewModel
import kotlinx.coroutines.flow.drop
import com.marvel.interfaces.OnItemClickListener
import com.marvel.ui.BaseFragment


class MarvelCharactersFragment : BaseFragment(), OnItemClickListener {
    private lateinit var binding: FragmentMarvelCharactersBinding
    private lateinit var charactersAdapter: CharactersAdapter

    /*lateinit var viewModel: CharactersViewModel*/
    private val viewModel: CharactersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding=FragmentMarvelCharactersBinding.inflate(layoutInflater)

        Log.d("herrr","createview")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("herrr","ViewCreated")

        setupViews()
    }
    override fun setupViews() {
        binding.charactersListview.layoutManager = LinearLayoutManager(context)
        showLoader()


        /*   viewModel = ViewModelProvider(this)[CharactersViewModel::class.java]
          viewModel.resultsListObserver.observe(this) { resultsList ->
               if (resultsList != null && resultsList.size > 0) {
                   charactersAdapter = CharactersAdapter(resultsList)
                   mainBinding.charactersListview.adapter = charactersAdapter
                   hideLoader()
               } else {
                   hideLoader()
                   mainBinding.txNoResult.visibility = View.VISIBLE
               }
           }*/


        // for flow with normal retrofit and RxJava adapter the same function
        charactersAdapter = CharactersAdapter(arrayListOf(),this)

   /*     charactersAdapter.setOnItemClickListener { character ->
            val bundle = Bundle().apply {
                putString(Constants.CharacterID, character.id.toString())
                putString(Constants.CharacterName, character.name)
            }
            findNavController().navigate(R.id.action_marvelCharactersFragment_to_characterDetailsFragment,bundle)
        }*/

        binding.charactersListview.adapter = charactersAdapter
        lifecycleScope.launchWhenResumed  {
            viewModel.makeApiCall()
            viewModel.resultsListObserver.drop(1).collect { resultsList ->
                if (resultsList.size > 0) {
                    charactersAdapter.setData(resultsList)
                    hideLoader()
                } else {
                    hideLoader()
                    binding.txNoResult.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun navigateToCharacterDetailsFragment(DataArrayList: Results) {
        Log.d("hhh","entered")
        val navController = Navigation.findNavController(binding.root)
        val bundle = Bundle()
        bundle.putString(Constants.CharacterID, DataArrayList.id.toString())
        bundle.putString(Constants.CharacterName, DataArrayList.name.toString())
        navController.navigate(R.id.action_marvelCharactersFragment_to_characterDetailsFragment,bundle)
    }
    override fun onStart() {
        super.onStart()
        Log.d("herrr","start")
    }

    override fun onPause() {
        super.onPause()
        Log.d("herrr","pause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("herrr","stop")
    }

    override fun onResume() {
        super.onResume()
        Log.d("herrr","resume")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("herrr","destroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("herrr","destroyView")
        parentFragmentManager.beginTransaction().show(this).commit()
    }

}