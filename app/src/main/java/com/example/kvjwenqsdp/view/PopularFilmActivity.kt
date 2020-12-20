package com.example.kvjwenqsdp.view

import com.example.kvjwenqsdp.R
import com.example.kvjwenqsdp.databinding.ActivityPopularFilmBinding
import com.example.kvjwenqsdp.viewmodel.PopularFilmActViewModel
import com.yenen.ahmet.basecorelibrary.base.ui.BaseDaggerActivity

class PopularFilmActivity :BaseDaggerActivity<PopularFilmActViewModel,ActivityPopularFilmBinding>(
    PopularFilmActViewModel::class.java,
    R.layout.activity_popular_film
){

    override fun initViewModel(viewModel: PopularFilmActViewModel) {
        binding.viewModel == viewModel
    }

}