package com.example.kvjwenqsdp.reository.network

import com.example.kvjwenqsdp.reository.network.entities.PopularFilmsResponse
import io.reactivex.Single

interface NetworkTmdbRepository {
    fun getPopularFilms( key: String,
                         language: String,
                         page: Int) : Single<PopularFilmsResponse>
}