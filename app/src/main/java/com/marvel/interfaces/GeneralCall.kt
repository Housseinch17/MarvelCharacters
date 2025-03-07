package com.marvel.interfaces

import com.marvel.beans.Characters
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import io.reactivex.rxjava3.core.Observable

interface GeneralCall {

    // this for normal use of retrofit
   /* @GET("characters")
    fun loadHome(@Query("apikey") apikey: String, @Query("ts") ts: String, @Query("hash") hash: String): Call<Characters>*/


    // this for normal use of retrofit
   /* @GET("characters/{characterId}/{type}")
    fun loadAll(@Path("characterId") characterId: String, @Path("type") type:String, @Query("apikey") apikey: String, @Query("ts") ts: String, @Query("hash") hash: String): Call<Characters>
*/


    // this for rx java adapters
    @GET("characters")
    fun loadHome(@Query("apikey") apikey: String, @Query("ts") ts: String, @Query("hash") hash: String): Observable<Characters>
    // this for rx java adapters
    @GET("characters/{characterId}/{type}")
    fun loadAll(@Path("characterId") characterId: String, @Path("type") type:String, @Query("apikey") apikey: String, @Query("ts") ts: String, @Query("hash") hash: String): Observable<Characters>



}