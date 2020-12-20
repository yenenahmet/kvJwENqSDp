package com.example.kvjwenqsdp.reository.network.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class PopularFilmsResponse(
    val page:Int,
    val results:List<PopularFilm>?,
    val total_pages:Int,
    val total_results:Int
)

@Parcelize
data class PopularFilm(
    val backdrop_path:String?,
    val first_air_date:String?,
    val genre_ids:List<Int>?,
    val id:Long?,
    val name:String?,
    val origin_country:List<String>?,
    val original_language:String?,
    val original_name:String?,
    val overview:String?,
    val popularity:String?,
    val poster_path:String?,
    val vote_average:Double?,
    val vote_count:Int?
): Parcelable