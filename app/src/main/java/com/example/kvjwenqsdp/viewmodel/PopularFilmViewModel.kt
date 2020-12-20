package com.example.kvjwenqsdp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kvjwenqsdp.adater.PopularFilmsAdapter
import com.example.kvjwenqsdp.reository.network.NetworkTmdbRepositoryImpl
import com.example.kvjwenqsdp.reository.network.entities.PopularFilm
import com.example.kvjwenqsdp.reository.network.api.TmdbService
import com.example.kvjwenqsdp.utils.NetworkUtil
import com.example.kvjwenqsdp.utils.ProjectUtil
import com.yenen.ahmet.basecorelibrary.base.utility.ProjectConstants
import com.yenen.ahmet.basecorelibrary.base.viewmodel.BaseRxViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import com.yenen.ahmet.basecorelibrary.base.model.LiveDataResponseModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PopularFilmViewModel @Inject constructor(private val networkTmdbRepositoryImpl: NetworkTmdbRepositoryImpl) :
    BaseRxViewModel() {

    val adapter = PopularFilmsAdapter()
    var page = 1
    var totalPages = 10
    private var totalResults = 200
    private var pageSize =20

    private var resultLiveDataPopularFilms: MutableLiveData<LiveDataResponseModel<List<PopularFilm>>>? =
        null

    fun getLiveDataPopularFilms(): LiveData<LiveDataResponseModel<List<PopularFilm>>> {
        if (resultLiveDataPopularFilms == null) {
            resultLiveDataPopularFilms = MutableLiveData()
            getPopularFilms(1)
        }
        return resultLiveDataPopularFilms!!
    }

    fun getPopularFilms(page: Int) {
        this.page = page
        addDisposable(
            networkTmdbRepositoryImpl.getPopularFilms(ProjectUtil.V3_KEY, "tr-TR", page)
                .subscribe({
                    this.totalPages = it.total_pages
                    this.totalResults = it.total_results
                    this.pageSize = totalResults / totalPages

                    if (it.results.isNullOrEmpty()) {
                        resultLiveDataPopularFilms?.value = LiveDataResponseModel(
                            null,
                            ProjectConstants.SUCCESS_STATUS_EMPTY_DATA, ""
                        )
                    } else {
                        this.page++
                        resultLiveDataPopularFilms?.value = LiveDataResponseModel(
                            it.results,
                            ProjectConstants.SUCCESS_STATUS, ""
                        )
                    }
                }, {
                    val errMessage = NetworkUtil.failMessage(it)
                    resultLiveDataPopularFilms?.value =
                        LiveDataResponseModel(null, ProjectConstants.ERROR_STATUS, errMessage)
                })
        )
    }
}