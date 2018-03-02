package com.task.paginationlist.presentation.piclist.views.paginator

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import butterknife.ButterKnife
import com.task.paginationlist.R
import kotlinx.android.synthetic.main.loader_view.view.*


class LoaderView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private var onClick: View.OnClickListener? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.loader_view, this, true)
        ButterKnife.bind(this)
        reloadBtn.setOnClickListener { v ->
            if (onClick != null) {
                onClick!!.onClick(v)
                errorContainer!!.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }
        }
    }

    fun setReloadClick(onClick: View.OnClickListener) {
        this.onClick = onClick
    }

    fun showError() {
        progressBar.visibility = View.GONE
        errorContainer!!.visibility = View.VISIBLE
    }

    fun showBar() {
        progressBar.visibility = View.VISIBLE
    }
}
