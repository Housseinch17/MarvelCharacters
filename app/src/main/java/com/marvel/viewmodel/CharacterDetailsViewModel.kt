package com.marvel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marvel.beans.Characters
import com.marvel.beans.Comics
import com.marvel.beans.Results
import com.marvel.config.Constants
import com.marvel.retrofit.APIUtils
import com.marvel.retrofit.HttpCallback
import com.marvel.retrofit.URLs
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call

class CharacterDetailsViewModel : ViewModel() {

/*
    private val characterId = MutableLiveData<String>()

    fun setCharacterId(id: String) {
        characterId.value = id
    }
    fun getCharacterId(): LiveData<String>? {
        return characterId
    }
    private var errorApi:String=""

    fun getErrorApi(): String? {
        return errorApi
    }
    private val characterName = MutableLiveData<String>()

    fun setCharacterName(name: String) {
        characterName.value = name
    }
    fun getCharacterName(): LiveData<String>? {
        return characterName
    }

    private val comicsList: MutableLiveData<ArrayList<Results>?>
    private val storiesList: MutableLiveData<ArrayList<Results>?>
    private val eventsList: MutableLiveData<ArrayList<Results>?>
    private val seriesList: MutableLiveData<ArrayList<Results>?>

    init {
        characterId.value
        characterName.value
        comicsList = MutableLiveData<ArrayList<Results>?>()
        storiesList = MutableLiveData<ArrayList<Results>?>()
        eventsList = MutableLiveData<ArrayList<Results>?>()
        seriesList = MutableLiveData<ArrayList<Results>?>()
    }

    val comicsListObserve: MutableLiveData<ArrayList<Results>?>
        get() = comicsList

    val storiesListObserve: MutableLiveData<ArrayList<Results>?>
        get() = storiesList

    val eventsListObserve: MutableLiveData<ArrayList<Results>?>
        get() = eventsList

    val seriesListObserve: MutableLiveData<ArrayList<Results>?>
        get() = seriesList

    fun loadComics() {
        val call = APIUtils().getService(URLs.baseURL).loadAll(this.characterId.value.toString(),"comics",Constants.publicKey, Constants.ts, Constants.getHash())
        call.enqueue(object : HttpCallback<Characters>() {
            override fun onRequestSuccess(call: Call<Characters>?, response: Characters?) {
                if (response != null ) {
                    comicsList.postValue(response.data.results)
                }
                else {
                    comicsList.postValue(java.util.ArrayList<Results>())
                }
            }

            override fun onRequestError(call: Call<Characters>?, response: String?) {
                comicsList.postValue(java.util.ArrayList<Results>())
                errorApi+="Comics"
            }
            override fun onRequestFail(call: Call<Characters>, error: String?) {
                comicsList.postValue(java.util.ArrayList<Results>())
                errorApi+="Comics"
            }
        })

    }
    fun loadEvents() {
        val call = APIUtils().getService(URLs.baseURL).loadAll(this.characterId.value.toString(),"events",Constants.publicKey, Constants.ts, Constants.getHash())
        call.enqueue(object : HttpCallback<Characters>() {
            override fun onRequestSuccess(call: Call<Characters>?, response: Characters?) {
                if (response != null ) {
                    eventsList.postValue(response.data.results)
                }
                else {
                    eventsList.postValue(java.util.ArrayList<Results>())
                }
            }

            override fun onRequestError(call: Call<Characters>?, response: String?) {
                eventsList.postValue(java.util.ArrayList<Results>())
                errorApi+=" ,Events"
            }
            override fun onRequestFail(call: Call<Characters>, error: String?) {
                eventsList.postValue(java.util.ArrayList<Results>())
                errorApi+=" ,Events"
            }
        })
    }
    fun loadSeries() {
        val call = APIUtils().getService(URLs.baseURL).loadAll(this.characterId.value.toString(),"series",Constants.publicKey, Constants.ts, Constants.getHash())
        call.enqueue(object : HttpCallback<Characters>() {
            override fun onRequestSuccess(call: Call<Characters>?, response: Characters?) {
                if (response != null ) {
                    seriesList.postValue(response.data.results)
                }
                else {
                    seriesList.postValue(java.util.ArrayList<Results>())
                }
            }

            override fun onRequestError(call: Call<Characters>?, response: String?) {
                seriesList.postValue(java.util.ArrayList<Results>())
                errorApi+=" ,Series"
            }
            override fun onRequestFail(call: Call<Characters>, error: String?) {
                seriesList.postValue(java.util.ArrayList<Results>())
                errorApi+=" ,Series"
            }
        })

    }
    fun loadStories() {
        val call = APIUtils().getService(URLs.baseURL).loadAll(this.characterId.value.toString(),"stories",Constants.publicKey, Constants.ts, Constants.getHash())
        call.enqueue(object : HttpCallback<Characters>() {
            override fun onRequestSuccess(call: Call<Characters>?, response: Characters?) {
                if (response != null ) {
                    storiesList.postValue(response.data.results)
                }
                else {
                    storiesList.postValue(java.util.ArrayList<Results>())
                }
            }

            override fun onRequestError(call: Call<Characters>?, response: String?) {
                storiesList.postValue(java.util.ArrayList<Results>())
                errorApi+=" ,Stories"
            }
            override fun onRequestFail(call: Call<Characters>, error: String?) {
                storiesList.postValue(java.util.ArrayList<Results>())
                errorApi+=" ,Stories"
            }
        })

    }
*/

    private val characterId = MutableStateFlow<String?>(null)

    fun setCharacterId(id: String) {
        characterId.value = id
    }

    fun getCharacterId(): Flow<String?> {
        return characterId
    }

    private var errorApi: String = ""

    fun getErrorApi(): String {
        return errorApi
    }

    private val characterName = MutableStateFlow<String?>(null)

    fun setCharacterName(name: String) {
        characterName.value = name
    }

    fun getCharacterName(): Flow<String?> {
        return characterName
    }


    private val comicsList: MutableStateFlow<ArrayList<Results>?> = MutableStateFlow(null)
    private val storiesList: MutableStateFlow<ArrayList<Results>?> = MutableStateFlow(null)
    private val eventsList: MutableStateFlow<ArrayList<Results>?> = MutableStateFlow(null)
    private val seriesList: MutableStateFlow<ArrayList<Results>?> = MutableStateFlow(null)


    val comicsListFlow: MutableStateFlow<ArrayList<Results>?>
        get() = comicsList

    val storiesListFlow: MutableStateFlow<ArrayList<Results>?>
        get() = storiesList

    val eventsListFlow: MutableStateFlow<ArrayList<Results>?>
        get() = eventsList

    val seriesListFlow: MutableStateFlow<ArrayList<Results>?>
        get() = seriesList

    //flow for normal retrofit
/*    fun loadComics() {
        val call = APIUtils().getService(URLs.baseURL).loadAll(
            characterId.value.toString(),
            "comics",
            Constants.publicKey,
            Constants.ts,
            Constants.getHash()
        )
        call.enqueue(object : HttpCallback<Characters>() {
            override fun onRequestSuccess(call: Call<Characters>?, response: Characters?) {
                if (response != null) {
                    comicsList.value = response.data.results
                } else {
                    comicsList.value =  arrayListOf()
                }
            }

            override fun onRequestError(call: Call<Characters>?, response: String?) {
                comicsList.value =  arrayListOf()
                errorApi += "Comics"
            }

            override fun onRequestFail(call: Call<Characters>, error: String?) {
                comicsList.value =  arrayListOf()
                errorApi += "Comics"
            }
        })
    }*/


    //flow for RxJava adapters
    fun loadComics() {
        val observable = APIUtils().getService(URLs.baseURL)
            .loadAll(
                characterId.value.toString(),
                "comics",
                Constants.publicKey,
                Constants.ts,
                Constants.getHash()
            )
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Characters> {
                override fun onSubscribe(d: Disposable) {
                    // You can use this to handle the disposable
                }

                override fun onNext(t: Characters) {
                    comicsList.value = t.data.results

                }

                override fun onError(e: Throwable) {
                    comicsList.value = arrayListOf()
                    errorApi += "Comics"
                }

                override fun onComplete() {
                }
            })
    }

        //flow for normal retrofit
        /* fun loadEvents() {
         val call = APIUtils().getService(URLs.baseURL).loadAll(
             characterId.value.toString(),
             "events",
             Constants.publicKey,
             Constants.ts,
             Constants.getHash()
         )
         call.enqueue(object : HttpCallback<Characters>() {
             override fun onRequestSuccess(call: Call<Characters>?, response: Characters?) {
                 if (response != null) {
                     eventsList.value = response.data.results
                 } else {
                     eventsList.value =  arrayListOf()
                 }
             }

             override fun onRequestError(call: Call<Characters>?, response: String?) {
                 eventsList.value =  arrayListOf()
                 errorApi += " ,Events"
             }

             override fun onRequestFail(call: Call<Characters>, error: String?) {
                 eventsList.value =  arrayListOf()
                 errorApi += " ,Events"
             }
         })
     }*/


//flow for RxJava adapters
    fun loadEvents() {
        val observable = APIUtils().getService(URLs.baseURL)
            .loadAll(
                characterId.value.toString(),
                "events",
                Constants.publicKey,
                Constants.ts,
                Constants.getHash()
            )
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Characters> {
                override fun onSubscribe(d: Disposable) {
                    // You can use this to handle the disposable
                }

                override fun onNext(t: Characters) {
                    eventsList.value = t.data.results

                }

                override fun onError(e: Throwable) {
                    eventsList.value = arrayListOf()
                    errorApi += "Comics"
                }

                override fun onComplete() {
                }
            })
    }


    //flow for normal retrofit
/*
    fun loadSeries() {
        val call = APIUtils().getService(URLs.baseURL).loadAll(
            characterId.value.toString(),
            "series",
            Constants.publicKey,
            Constants.ts,
            Constants.getHash()
        )
        call.enqueue(object : HttpCallback<Characters>() {
            override fun onRequestSuccess(call: Call<Characters>?, response: Characters?) {
                if (response != null) {
                    seriesList.value = response.data.results
                } else {
                    seriesList.value =  arrayListOf()
                }
            }

            override fun onRequestError(call: Call<Characters>?, response: String?) {
                seriesList.value =  arrayListOf()
                errorApi += " ,Series"
            }

            override fun onRequestFail(call: Call<Characters>, error: String?) {
                seriesList.value =  arrayListOf()
                errorApi += " ,Series"


            }
        })
    }
*/

    //flow for RxJava adapters
    fun loadSeries() {
        val observable = APIUtils().getService(URLs.baseURL)
            .loadAll(
                characterId.value.toString(),
                "series",
                Constants.publicKey,
                Constants.ts,
                Constants.getHash()
            )
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Characters> {
                override fun onSubscribe(d: Disposable) {
                    // You can use this to handle the disposable
                }

                override fun onNext(t: Characters) {
                    seriesList.value = t.data.results

                }

                override fun onError(e: Throwable) {
                    seriesList.value = arrayListOf()
                    errorApi += "Comics"
                }

                override fun onComplete() {
                }
            })
    }


        //flow for normal retrofit

/*
    fun loadStories() {
        val call = APIUtils().getService(URLs.baseURL).loadAll(
            characterId.value.toString(),
            "stories",
            Constants.publicKey,
            Constants.ts,
            Constants.getHash()
        )
        call.enqueue(object : HttpCallback<Characters>() {
            override fun onRequestSuccess(call: Call<Characters>?, response: Characters?) {
                if (response != null) {
                    storiesList.value = response.data.results
                } else {
                    storiesList.value =  arrayListOf()
                }
            }

            override fun onRequestError(call: Call<Characters>?, response: String?) {
                storiesList.value =  arrayListOf()
                errorApi += " ,Stories"
            }

            override fun onRequestFail(call: Call<Characters>, error: String?) {
                storiesList.value =  arrayListOf()
                errorApi += " ,Stories"

            }
        })
    }
*/

    //flow for RxJava adapters
    fun loadStories() {
        val observable = APIUtils().getService(URLs.baseURL)
            .loadAll(
                characterId.value.toString(),
                "stories",
                Constants.publicKey,
                Constants.ts,
                Constants.getHash()
            )
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Characters> {
                override fun onSubscribe(d: Disposable) {
                    // You can use this to handle the disposable
                }

                override fun onNext(t: Characters) {
                    storiesList.value = t.data.results

                }

                override fun onError(e: Throwable) {
                    storiesList.value = arrayListOf()
                    errorApi += "Comics"
                }

                override fun onComplete() {
                }
            })
    }
}