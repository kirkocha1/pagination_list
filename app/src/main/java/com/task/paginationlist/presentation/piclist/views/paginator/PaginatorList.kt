package com.task.paginationlist.presentation.piclist.views.paginator

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout

import com.task.paginationlist.R

import butterknife.BindView
import butterknife.ButterKnife

/**
 * Created by kirill on 07.08.17.
 */

class PaginatorList : FrameLayout {

    @BindView(R.id.pic_list)
    internal var list: RecyclerView? = null

    @BindView(R.id.swipe_refresh)
    internal var swipeRefresh: SwipeRefreshLayout? = null

    private var manager: LinearLayoutManager? = null

    private var isAllLoaded: Boolean = false
    private var isLoading: Boolean = false
    private var isError = false
    private var listener: OnLoadMoreListener? = null

    val isRefreshing: Boolean
        get() = swipeRefresh!!.isRefreshing

    val lastListItemPosition: Int
        get() = if (manager != null) manager!!.findLastVisibleItemPosition() else 0

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    fun setManager(manager: LinearLayoutManager) {
        this.manager = manager
        list!!.layoutManager = manager
    }

    fun setLoadMoreListener(listener: OnLoadMoreListener) {
        this.listener = listener
    }

    fun setAllLoaded(allLoaded: Boolean) {
        isAllLoaded = allLoaded
    }

    fun setAdapter(adapter: RecyclerView.Adapter<*>) {
        list!!.adapter = adapter
    }

    fun onCompleteLoad() {
        swipeRefresh!!.isRefreshing = false
        swipeRefresh!!.isEnabled = true
        isLoading = false
        isError = false
    }

    fun onErrorLoad() {
        onCompleteLoad()
        isError = true
    }

    fun setLastVisiblePosition(position: Int) {
        if (manager != null) {
            list!!.post { manager!!.scrollToPosition(position) }
        }
    }

    private fun init(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.paginator_list_view, this, true)
        ButterKnife.bind(this)
        list!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (manager != null && list!!.adapter != null) {
                    val visibleItemCount = manager!!.childCount
                    val firstVisibleItemPosition = manager!!.findFirstVisibleItemPosition()
                    val totalItemCount = manager!!.itemCount
                    if (!isAllLoaded && !isError && !isLoading && list!!.adapter.itemCount != 1) {
                        if (visibleItemCount + firstVisibleItemPosition >= totalItemCount) {//list.getAdapter().getItemCount()) {
                            isLoading = true
                            if (listener != null) {
                                listener!!.loadMoreData(list!!.adapter.itemCount / Config.LIMIT + 1)
                            }
                        }
                    }
                }
            }
        })

        swipeRefresh!!.setOnRefreshListener {
            swipeRefresh!!.isEnabled = false
            isAllLoaded = false
            isError = false
            if (listener != null) {
                listener!!.loadMoreData(1)
            }
        }
    }

    interface OnLoadMoreListener {
        fun loadMoreData(page: Int)
    }
}
