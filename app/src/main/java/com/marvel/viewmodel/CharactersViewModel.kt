package com.marvel.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marvel.beans.Characters
import com.marvel.beans.Results
import com.marvel.config.Constants
import com.marvel.retrofit.APIUtils
import com.marvel.retrofit.HttpCallback
import com.marvel.retrofit.URLs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
class CharactersViewModel : ViewModel() {

/*    private val resultsList: MutableLiveData<ArrayList<Results>?> =
        MutableLiveData<ArrayList<Results>?>()

    val resultsListObserver: MutableLiveData<ArrayList<Results>?>
        get() = resultsList

    fun makeApiCall() {
        val call = APIUtils().getService(URLs.baseURL)
            .loadHome(Constants.publicKey, Constants.ts, Constants.getHash())
        call.enqueue(object : HttpCallback<Characters>() {
            override fun onRequestSuccess(call: Call<Characters>?, response: Characters?) {
                if (response != null) {
                    resultsList.postValue(response.data.results)
                } else {
                    resultsList.postValue(java.util.ArrayList<Results>())
                }
            }

            override fun onRequestError(call: Call<Characters>?, response: String?) {
                resultsList.postValue(java.util.ArrayList<Results>())
            }

            override fun onRequestFail(call: Call<Characters>, error: String?) {
                resultsList.postValue(java.util.ArrayList<Results>())
            }
        })
    }*/


    val resultsList = MutableStateFlow<ArrayList<Results>>(arrayListOf())

    val resultsListObserver: MutableStateFlow<ArrayList<Results>>
        get() = resultsList


    //flow for normal retrofit use

       /* fun makeApiCall() {
            val call = APIUtils().getService(URLs.baseURL)
                .loadHome(Constants.publicKey, Constants.ts, Constants.getHash())
            call.enqueue(object : HttpCallback<Characters>() {
                override fun onRequestSuccess(call: Call<Characters>?, response: Characters?) {
                    if (response != null) {
                        resultsList.value = response.data.results as ArrayList<Results>
                    } else {
                        resultsList.value = arrayListOf()
                    }
                }

                override fun onRequestError(call: Call<Characters>?, response: String?) {
                    resultsList.value = arrayListOf()
                }

                override fun onRequestFail(call: Call<Characters>, error: String?) {
                    resultsList.value = arrayListOf()
                }
            })
        }*/


    //flow for rxjava suspend adapters
    fun makeApiCall() {
        val observable = APIUtils().getService(URLs.baseURL)
            .loadHome(Constants.publicKey, Constants.ts, Constants.getHash())

        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Characters> {
                override fun onSubscribe(d: Disposable) {
                    // You can use this to handle the disposable
                }

                override fun onNext(response: Characters) {
                    resultsList.value = response.data.results as ArrayList<Results>
                }

                override fun onError(e: Throwable) {
                    resultsList.value = arrayListOf()
                }

                override fun onComplete() {
                    // You can use this to handle the completion event
                }
            })
    }

}