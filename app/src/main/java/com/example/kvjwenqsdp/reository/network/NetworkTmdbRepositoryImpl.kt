package com.example.kvjwenqsdp.reository.network

import com.example.kvjwenqsdp.reository.network.api.TmdbService
import com.example.kvjwenqsdp.reository.network.entities.PopularFilmsResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NetworkTmdbRepositoryImpl (private val  service: TmdbService) :NetworkTmdbRepository {

    override fun getPopularFilms(key: String,
                                 language: String,
                                 page: Int): Single<PopularFilmsResponse> {
        return service.getPopularFilms(key, language, page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    } // CallBack

}

