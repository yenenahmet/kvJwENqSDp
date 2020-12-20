package com.example.kvjwenqsdp.view

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.kvjwenqsdp.R
import com.example.kvjwenqsdp.databinding.FragmentPopulerFilmDetailBinding
import com.example.kvjwenqsdp.reository.network.entities.PopularFilm
import com.example.kvjwenqsdp.utils.ProjectUtil
import com.example.kvjwenqsdp.viewmodel.PopularFilmDetailViewModel
import com.yenen.ahmet.basecorelibrary.base.ui.BaseDaggerFragment

class PopularFilmDetailFragment :
    BaseDaggerFragment<PopularFilmDetailViewModel, FragmentPopulerFilmDetailBinding>(
        PopularFilmDetailViewModel::class.java,
        R.layout.fragment_populer_film_detail
    ) {

    override fun initViewModel(viewModel: PopularFilmDetailViewModel) {
        binding.viewModel = viewModel
    }

    override fun onBundle(bundle: Bundle) {
        val popularFilm = bundle.getParcelable<PopularFilm>("POPULAR_FILM")
        val imgPath =
            ProjectUtil.IMG_URL_ORIGINAL.replace(":{path}", popularFilm?.backdrop_path ?: "")
        Glide.with(binding.root.context).load(imgPath).fitCenter().into(binding.imgBackdrop)
        binding.tvFilmName.text = popularFilm?.name ?: ""
        binding.tvFilmAverage.text = popularFilm?.vote_average.toString()
        binding.tvOverview.text = popularFilm?.overview ?: ""
        binding.tvFirstAirDate.text = popularFilm?.first_air_date ?: ""
        binding.tvOriginalLanguage.text = popularFilm?.original_language ?: ""
        binding.tvOriginalName.text = popularFilm?.original_name ?: ""
        binding.toolbar.title = popularFilm?.original_name ?: ""
    }

    override fun onBindingCreate(binding: FragmentPopulerFilmDetailBinding) {
        binding.toolbar.setNavigationIcon(R.drawable.back)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }


}