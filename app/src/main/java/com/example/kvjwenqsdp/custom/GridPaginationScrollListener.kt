package com.example.kvjwenqsdp.custom

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class GridPaginationScrollListener constructor(private val layoutManager: GridLayoutManager) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if(dy>0){
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val lastItem = layoutManager.findLastCompletelyVisibleItemPosition()
            val total = lastItem + visibleItemCount

            if(!isLastPage() && !isLoading() && total>= totalItemCount){
                loadMoreItems()
            }

        }
    }

    protected abstract fun loadMoreItems()

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean
}