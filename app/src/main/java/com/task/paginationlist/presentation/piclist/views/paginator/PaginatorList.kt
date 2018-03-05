package com.task.paginationlist.presentation.piclist.views.paginator

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.task.paginationlist.R
import kotlinx.android.synthetic.main.paginator_list_view.view.*

/**
 * Created by kirill on 07.08.17.
 */

class PaginatorList(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private var manager: LinearLayoutManager? = null

    private var isAllLoaded: Boolean = false
    private var isLoading: Boolean = false
    private var isError = false
    private var listener: OnLoadMoreListener? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.paginator_list_view, this, true)
        picList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (manager != null && picList.adapter != null) {
                    val visibleItemCount = manager!!.childCount
                    val firstVisibleItemPosition = manager!!.findFirstVisibleItemPosition()
                    val totalItemCount = manager!!.itemCount
                    if (!isAllLoaded && !isError && !isLoading && picList.adapter.itemCount != 1) {
                        if (visibleItemCount + firstVisibleItemPosition >= totalItemCount) {//list.getAdapter().getItemCount()) {
                            isLoading = true
                            if (listener != null) {
                                listener?.loadMoreData(picList.adapter.itemCount / Config.LIMIT + 1)
                            }
                        }
                    }
                }
            }
        })

        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isEnabled = false
            isAllLoaded = false
            isError = false
            listener?.loadMoreData(1)
        }
    }

    fun isRefreshing() = swipeRefresh.isRefreshing

    fun lastListItemPosition() = if (manager != null) manager!!.findLastVisibleItemPosition() else 0

    fun setManager(manager: LinearLayoutManager) {
        this.manager = manager
        picList.layoutManager = manager
    }

    fun setLoadMoreListener(listener: OnLoadMoreListener) {
        this.listener = listener
    }

    fun setAllLoaded(allLoaded: Boolean) {
        isAllLoaded = allLoaded
    }

    fun setAdapter(adapter: RecyclerView.Adapter<*>) {
        picList.adapter = adapter
    }

    fun onCompleteLoad() {
        swipeRefresh.isRefreshing = false
        swipeRefresh.isEnabled = true
        isLoading = false
        isError = false
    }

    fun onErrorLoad() {
        onCompleteLoad()
        isError = true
    }

    fun setLastVisiblePosition(position: Int) {
        if (manager != null) {
            picList.post { manager!!.scrollToPosition(position) }
        }
    }

    interface OnLoadMoreListener {
        fun loadMoreData(page: Int)
    }
}
