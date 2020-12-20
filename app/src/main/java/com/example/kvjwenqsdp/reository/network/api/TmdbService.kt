package com.example.kvjwenqsdp.reository.network.api

import com.example.kvjwenqsdp.reository.network.entities.PopularFilmsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbService {


    @GET("3/tv/popular")
    fun getPopularFilms(
        @Query("api_key") key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<PopularFilmsResponse>


}