package com.task.paginationlist.presentation.piclist.views.paginator

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView

import com.task.paginationlist.R

import butterknife.BindView
import butterknife.ButterKnife


class LoaderView : FrameLayout {

    @BindView(R.id.progress_bar)
    var bar: ProgressBar? = null

    @BindView(R.id.reload_btn)
    var reload: ImageView? = null

    @BindView(R.id.error_container)
    var errorContainer: LinearLayout? = null

    @BindView(R.id.error_message)
    var error: TextView? = null

    private var onClick: View.OnClickListener? = null

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    private fun init(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.loader_view, this, true)
        ButterKnife.bind(this)
        reload!!.setOnClickListener { v ->
            if (onClick != null) {
                onClick!!.onClick(v)
                errorContainer!!.visibility = View.GONE
                bar!!.visibility = View.VISIBLE
            }
        }
    }

    fun setReloadClick(onClick: View.OnClickListener) {
        this.onClick = onClick
    }

    fun showError() {
        bar!!.visibility = View.GONE
        errorContainer!!.visibility = View.VISIBLE
    }

    fun showBar() {
        bar!!.visibility = View.VISIBLE
    }
}
