package com.task.paginationlist.presentation.piclist.views.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.task.paginationlist.PaginationListApplication
import com.task.paginationlist.R
import com.task.paginationlist.data.db.models.WallpaperDb
import com.task.paginationlist.data.utils.ILoader
import com.task.paginationlist.presentation.piclist.interfaces.OnPickClickListener
import com.task.paginationlist.presentation.piclist.views.activities.interfaces.IPaginatorAdapter
import com.task.paginationlist.presentation.piclist.views.adapters.holders.LoadingHolder
import com.task.paginationlist.presentation.piclist.views.adapters.holders.PicViewHolder
import com.task.paginationlist.presentation.piclist.views.paginator.Config

import java.text.ParseException

class PicAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), IPaginatorAdapter {

    private var isSuccessful = true
    private var reload: View.OnClickListener? = null
    private var onPicClick: OnPickClickListener? = null
    private var isFooterVisible: Boolean = false

    var loader: ILoader = PaginationListApplication.component!!.loader

    private var wallpapers: MutableList<WallpaperDb>? = null

    fun addData(models: MutableList<WallpaperDb>?, isRefreshing: Boolean) {
        if (isRefreshing) {
            this.wallpapers = null
        }
        if (models != null) {
            if (this.wallpapers == null) {
                this.wallpapers = models
            } else {
                this.wallpapers!!.addAll(models)
            }
        }
        isSuccessful = true
        notifyDataSetChanged()
    }

    override fun setReload(listener: View.OnClickListener) {
        this.reload = listener
    }

    override fun setOnPicClick(onPicClick: OnPickClickListener) {
        this.onPicClick = onPicClick
    }

    override fun setSuccessful(successful: Boolean) {
        isSuccessful = successful
        notifyItemChanged(itemCount - 1)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (isFooter(position)) {
            val loadingHolder = holder as LoadingHolder
            reload?.let {
                loadingHolder.loaderView!!.setReloadClick(it)
            }
            loadingHolder.loaderView!!.visibility = if (isFooterVisible) View.VISIBLE else View.GONE
            if (!isSuccessful) {
                loadingHolder.loaderView!!.showError()
            } else {
                holder.loaderView!!.showBar()
            }
        } else {
            val picHolder = holder as PicViewHolder
            loader.loadImage(wallpapers!![position].thumbUrl!!, picHolder.image)
            picHolder.image.setOnClickListener { v ->
                if (onPicClick != null) {
                    onPicClick!!.onPickClick(v, position, wallpapers!![position].id, wallpapers!!.size)
                }
            }
            var text = ""
            try {
                wallpapers!![position].creationDate?.let {
                    text = Config.formatDate(it)
                }

            } catch (e: ParseException) {
                Log.e(TAG, e.message)
            }

            picHolder.date.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == FOOTER_TYPE)
            LoadingHolder(LayoutInflater.from(parent.context).inflate(R.layout.footer_item, parent, false))
        else
            PicViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.pic_list_item, parent, false))
    }

    override fun getItemViewType(position: Int): Int {
        return if (isFooter(position)) FOOTER_TYPE else ITEM_TYPE
    }

    override fun setFooterVisibility(isAllLoaded: Boolean) {
        isFooterVisible = !isAllLoaded
    }

    private fun isFooter(position: Int): Boolean {
        return position == itemCount - 1
    }

    override fun realItemCount(): Int {
        return itemCount - 1
    }

    override fun getItemCount(): Int {
        return if (wallpapers != null) wallpapers!!.size + 1 else 1
    }

    companion object {

        private val TAG = "PicAdapter"
        val ITEM_TYPE = 1
        val FOOTER_TYPE = 0
    }
}
