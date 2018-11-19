package com.dicoding.muadz.footballmatchschedule.ApiUtils

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface SportDBServices{
    @GET("api/v1/json/{key}/eventspastleague.php?id=4328")
    fun lastMatchList(@Path("key") key: String): Call<ResponseBody>

    @GET("api/v1/json/{key}/eventsnextleague.php?id=4328")
    fun nextMatchList(@Path("key") key: String): Call<ResponseBody>

    @GET("api/v1/json/{key}/lookupevent.php?id={id}")
    fun matchDetail(@Path("key") key: String, @Path("id") id: String): Call<ResponseBody>
}