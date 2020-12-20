package com.example.kvjwenqsdp.adater

import com.bumptech.glide.Glide
import com.example.kvjwenqsdp.R
import com.example.kvjwenqsdp.databinding.ItemPopularFilmBinding
import com.example.kvjwenqsdp.reository.network.entities.PopularFilm
import com.example.kvjwenqsdp.utils.ProjectUtil
import com.yenen.ahmet.basecorelibrary.base.adapter.BaseViewBindingRecyclerViewAdapter

class PopularFilmsAdapter :BaseViewBindingRecyclerViewAdapter<PopularFilm,ItemPopularFilmBinding>(
    R.layout.item_popular_film
) {

    override fun setBindingModel(
        item: PopularFilm,
        binding: ItemPopularFilmBinding,
        position: Int
    ) {
        binding.tvFilmName.text = item.name
        binding.tvFilmAverage.text = item.vote_average.toString()
        binding.tvOverview.text = item.overview
        val path = ProjectUtil.IMG_URL_POSTER.replace(":{path}",item.poster_path?:"")
        Glide.with(binding.root.context).load(path).fitCenter().into(binding.imgPoster)
    }


}