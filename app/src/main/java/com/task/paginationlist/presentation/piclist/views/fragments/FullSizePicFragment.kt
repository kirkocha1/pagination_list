package com.task.paginationlist.presentation.piclist.views.fragments

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.arellomobile.mvp.MvpAppCompatFragment
import com.task.paginationlist.R
import com.task.paginationlist.presentation.piclist.views.adapters.PicFragmentPagerAdapter
import com.task.paginationlist.presentation.piclist.views.paginator.Config
import com.task.paginationlist.presentation.piclist.views.utils.UtilsHelper

import butterknife.BindView
import butterknife.ButterKnife

class FullSizePicFragment : MvpAppCompatFragment() {

    @BindView(R.id.view_pager)
    internal var pager: ViewPager? = null

    internal var adapter: PicFragmentPagerAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_full_pic, container, false)
        ButterKnife.bind(this, view)
        initUi()
        return view
    }

    private fun initUi() {
        adapter = PicFragmentPagerAdapter(fragmentManager, if (arguments != null) arguments.getInt(PICS_COUNT) else Config.LIMIT)
        pager!!.adapter = adapter
        pager!!.currentItem = if (arguments != null) arguments.getInt(PIC_POSITION) else 0
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState!!.putInt(CURRENT_ITEM_POS, pager!!.currentItem)
        outState.putInt(CURRENT_ITEM_COUNT, adapter.count)
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null && pager != null) {
            adapter.count = savedInstanceState.getInt(CURRENT_ITEM_COUNT)
            pager!!.currentItem = savedInstanceState.getInt(CURRENT_ITEM_POS)
        }
    }

    fun updateCount(count: Int) {
        adapter.count = count
    }

    fun setCurrentPic(pos: Int) {
        pager!!.currentItem = pos
    }

    companion object {
        val CURRENT_ITEM_POS = "current_item_pos"
        val CURRENT_ITEM_COUNT = "current_item_count"
        val PIC_POSITION = "pic_position"
        val PICS_COUNT = "pics_count"
        val PIC_ID = "pic_id"

        fun createFullSizePicFragment(pos: Int, id: Int, count: Int): FullSizePicFragment {
            val fragment = FullSizePicFragment()
            val bundle = Bundle()
            bundle.putInt(PIC_POSITION, pos)
            bundle.putInt(PIC_ID, id)
            bundle.putInt(PICS_COUNT, count)
            fragment.arguments = bundle
            return fragment
        }
    }
}
