package com.example.kvjwenqsdp.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kvjwenqsdp.R
import com.example.kvjwenqsdp.custom.GridPaginationScrollListener
import com.example.kvjwenqsdp.databinding.FragmentPopularFilmBinding
import com.example.kvjwenqsdp.databinding.ItemPopularFilmBinding
import com.example.kvjwenqsdp.reository.network.entities.PopularFilm
import com.example.kvjwenqsdp.viewmodel.PopularFilmViewModel
import com.google.android.material.snackbar.Snackbar
import com.yenen.ahmet.basecorelibrary.base.adapter.BaseViewBindingRecyclerViewAdapter
import com.yenen.ahmet.basecorelibrary.base.ui.BaseDaggerFragment
import com.yenen.ahmet.basecorelibrary.base.utility.ProjectConstants
import java.lang.Exception

class PopularFilmFragment : BaseDaggerFragment<PopularFilmViewModel, FragmentPopularFilmBinding>(
    PopularFilmViewModel::class.java,
    R.layout.fragment_popular_film
), BaseViewBindingRecyclerViewAdapter.ClickListener<PopularFilm, ItemPopularFilmBinding> {

    private var isLoadingPopularFilm = false
    private var paginationScrollListener: GridPaginationScrollListener? = null

    override fun initViewModel(viewModel: PopularFilmViewModel) {
        binding.viewModel = viewModel
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.adapter.setListener(this)
    }

    override fun createLiveData(lifecycleOwner: LifecycleOwner) {
        viewModel.getLiveDataPopularFilms().observe(lifecycleOwner, Observer {
            it?.let {
                if (it.status == ProjectConstants.SUCCESS_STATUS) {
                    it.data?.let {
                        viewModel.adapter.addItems(it)
                    }
                } else if(it.status == ProjectConstants.ERROR_STATUS) {
                    showSnackBar(it.description?:"")
                }
                isLoadingPopularFilm = false
                binding.progressWheel.visibility = View.GONE
                binding.progressWheel.stopSpinning()
            }
        })
    }


    override fun onBindingCreate(binding: FragmentPopularFilmBinding) {
        loadingPopularFilm()
    }

    private fun loadingPopularFilm() {
        val manager = binding.rvPopularFilm.layoutManager as GridLayoutManager
        paginationScrollListener = object : GridPaginationScrollListener(manager) {
            override fun isLastPage(): Boolean {
                return viewModel.page >= viewModel.totalPages
            }

            override fun isLoading(): Boolean {
                return isLoadingPopularFilm
            }

            override fun loadMoreItems() {
                isLoadingPopularFilm = true
                binding.progressWheel.visibility = View.VISIBLE
                binding.progressWheel.spin()
                viewModel.getPopularFilms(viewModel.page)
            }
        }

        binding.rvPopularFilm.addOnScrollListener(paginationScrollListener!!)
    }

    override fun onDetach() {
        paginationScrollListener?.let {
            binding.rvPopularFilm.removeOnScrollListener(it)
            paginationScrollListener = null
        }
        super.onDetach()
    }

    override fun onItemClick(item: PopularFilm, position: Int, rowBinding: ItemPopularFilmBinding) {
        try{
            val bundle = Bundle().apply {
                putParcelable("POPULAR_FILM",item)
            }
            findNavController().navigate(R.id.action_popularFilmFragment_to_popularFilmDetailFragment,bundle)
        }catch (ignored:Exception){}
    }

    private fun showSnackBar(text:String){
        try {
            Snackbar.make(binding.root,text,Snackbar.LENGTH_LONG).show()
        }catch (ignored:Exception){
        }
    }
}