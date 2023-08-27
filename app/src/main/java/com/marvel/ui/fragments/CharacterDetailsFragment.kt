package com.marvel.ui.fragments

import AllCategories
import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.marvel.R
import com.marvel.adapters.all.AllCategoriesAdapter
import com.marvel.config.Constants
import com.marvel.databinding.FragmentCharacterDetailsBinding
import com.marvel.interfaces.ItemListener
import com.marvel.ui.BaseFragment
import com.marvel.viewmodel.CharacterDetailsViewModel
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.take

class CharacterDetailsFragment : BaseFragment(), ItemListener {
    private lateinit var activityCharacterDetailsBinding: FragmentCharacterDetailsBinding
    /*lateinit var viewModel: CharacterDetailsViewModel*/
    private val viewModel: CharacterDetailsViewModel by viewModels()
    private lateinit var characterId: String
    private lateinit var characterName: String
    private lateinit var categoriesAr: ArrayList<AllCategories>
    private lateinit var updatedList: ArrayList<AllCategories>
    private lateinit var errorApi:String
    private lateinit var allCategoriesAdapter: AllCategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activityCharacterDetailsBinding=FragmentCharacterDetailsBinding.inflate(layoutInflater)

        categoriesAr = ArrayList()
        activityCharacterDetailsBinding.charactersDetailsListview.layoutManager = LinearLayoutManager(requireContext())
        allCategoriesAdapter = AllCategoriesAdapter(categoriesAr,this)
        activityCharacterDetailsBinding.charactersDetailsListview.adapter = allCategoriesAdapter

        setupViews()

        return activityCharacterDetailsBinding.root
    }
    @SuppressLint("SetTextI18n")
    override fun setupViews() {
        /*viewModel = ViewModelProvider(this)[CharacterDetailsViewModel::class.java]*/
        /*
        val htmlString = "<font color=\"red\">$characterId</font>"
        val spanned = Html.fromHtml(htmlString)
        activityCharacterDetailsBinding.txCharacterId.append(spanned)
*/

        activityCharacterDetailsBinding.toolbarDetails.backBtn.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        updatedList = ArrayList()
//        viewModel.getCharacterId()?.observe(this,{
//            if(it.isNotEmpty())
//                activityCharacterDetailsBinding.txCharacterId.text="CharacterId: $characterId"
//        })

        lifecycleScope.launchWhenStarted {
            viewModel.getCharacterId().collect { it ->
                if (it != null && it.isNotEmpty()) {
                    activityCharacterDetailsBinding.txCharacterId.text = "${R.string.character_id}: "
                    val htmlString = "<font color=\"red\">$characterId</font>"
                    val spanned = Html.fromHtml(htmlString)
                    activityCharacterDetailsBinding.txCharacterId.append(spanned)
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.getCharacterName().collect {
                if (it != null && it.isNotEmpty())
                    activityCharacterDetailsBinding.toolbarDetails.toolbarTitle.text = characterName
            }
        }
        /*   viewModel.getCharacterName()?.observe(this,{
            if(it.isNotEmpty())
                activityCharacterDetailsBinding.toolbarDetails.toolbarTitle.text="$characterName"
        })*/

        arguments?.let { bundle ->
            characterId = bundle.getString(Constants.CharacterID).toString()
            viewModel.setCharacterId(characterId)
            characterName = bundle.getString(Constants.CharacterName).toString()
            viewModel.setCharacterName(characterName)
        }
        showLoader()

        /*   viewModel.comicsListObserve.observe(this, Observer { comicsList ->
            if (!comicsList.isNullOrEmpty()) {
                updatedList.add(AllCategories("Comics", comicsList))
                Log.d("first","comics")
            } else {
                //Toast.makeText(this@CharacterDetailsActivity, "No result", Toast.LENGTH_LONG).show()
            }
            viewModel.loadEvents()
            Log.d("first","loadEvents")

        })*/

        viewModel.loadComics()

        lifecycleScope.launchWhenCreated {
            viewModel.comicsListFlow.drop(1).take(1).collect { comicsList ->
                if (!comicsList.isNullOrEmpty()) {
                    updatedList.add(AllCategories("Comics", comicsList))
                    Log.d("first", "comics")
                }
                viewModel.loadEvents()
                Log.d("first", "loadComics")
            }
        }

        lifecycleScope.launchWhenCreated {

            viewModel.eventsListFlow.drop(1).take(1).collect  { eventsList ->
                if (!eventsList.isNullOrEmpty()) {
                    updatedList.add(AllCategories("Events", eventsList))
                    Log.d("first", "events")
                }
                viewModel.loadSeries()
                Log.d("first", "loadSeries")
            }
        }

        lifecycleScope.launchWhenCreated {

            viewModel.seriesListFlow.drop(1).take(1).collect  { seriesList ->
                if (!seriesList.isNullOrEmpty()) {
                    updatedList.add(AllCategories("Series", seriesList))
                    Log.d("first", "series")
                }
                viewModel.loadStories()
                Log.d("first", "loadEvents")
            }
        }

        lifecycleScope.launchWhenCreated {

            viewModel.storiesListFlow.drop(1).take(1).collect  { storiesList ->
                if (!storiesList.isNullOrEmpty()) {
                    updatedList.add(AllCategories("Stories", storiesList))
                    Log.d("first", "stories")
                }
                hideLoader()
                errorApi = viewModel.getErrorApi()
                if (errorApi.isNotEmpty())
                    apiPopUP(errorApi)

                parseData()
            }
        }

        /*   viewModel.eventsListObserve.observe(this, Observer { eventsList ->
               if (!eventsList.isNullOrEmpty()) {
                   updatedList.add(AllCategories("Events", eventsList))
                   Log.d("first","Events")
               } else {
                   //Toast.makeText(this@CharacterDetailsActivity, "No result", Toast.LENGTH_LONG).show()
               }
               viewModel.loadSeries()
               Log.d("first","loadSeries")
           })*/

        /*   viewModel.seriesListObserve.observe(this, Observer { seriesList ->
               if (!seriesList.isNullOrEmpty()) {
                   updatedList.add(AllCategories("Series", seriesList))
                   Log.d("first","Series")

               } else {
                   //Toast.makeText(this@CharacterDetailsActivity, "No result", Toast.LENGTH_LONG).show()
               }
               viewModel.loadStories()
               Log.d("first","loadStories")

           })*/

/*        viewModel.storiesListObserve.observe(this, Observer { storiesList ->
            if (!storiesList.isNullOrEmpty()) {
                hideLoader()
                updatedList.add(AllCategories("Stories", storiesList))
                Log.d("first","Stories")

            } else {
                hideLoader()
                Log.d("first","hideLoader")
                //Toast.makeText(this@CharacterDetailsActivity, "No result", Toast.LENGTH_LONG).show()
            }
            errorApi=viewModel.getErrorApi().toString()
            if(errorApi.isNotEmpty())
                apiPopUP(errorApi)

            parseData()
            Log.d("first","parseData")
        })
        viewModel.loadComics()*/

    }

    private fun parseData() {
        if(updatedList.isEmpty()){
            activityCharacterDetailsBinding.txNoResult.visibility = View.VISIBLE
            return
        }
        else {
            allCategoriesAdapter.updateList(updatedList)
        }
    }

    override fun navigateToDetailsFragment(bundle: Bundle) {
        val navController = Navigation.findNavController(activityCharacterDetailsBinding.root)
        navController.navigate(R.id.action_characterDetailsFragment_to_detailsFragment,bundle)
    }
}