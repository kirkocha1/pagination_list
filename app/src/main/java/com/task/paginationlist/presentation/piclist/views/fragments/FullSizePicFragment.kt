package com.task.paginationlist.presentation.piclist.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.task.paginationlist.R
import com.task.paginationlist.presentation.piclist.views.adapters.PicFragmentPagerAdapter
import com.task.paginationlist.presentation.piclist.views.paginator.Config
import kotlinx.android.synthetic.main.fragment_full_pic.*

class FullSizePicFragment : MvpAppCompatFragment() {

    lateinit var adapter: PicFragmentPagerAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_full_pic, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        adapter = PicFragmentPagerAdapter(fragmentManager, if (arguments != null) arguments.getInt(PICS_COUNT) else Config.LIMIT)
        viewPager.adapter = adapter
        viewPager.currentItem = if (arguments != null) arguments.getInt(PIC_POSITION) else 0
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState!!.putInt(CURRENT_ITEM_POS, viewPager.currentItem)
        outState.putInt(CURRENT_ITEM_COUNT, adapter.count)
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null && viewPager != null) {
            adapter.count = savedInstanceState.getInt(CURRENT_ITEM_COUNT)
            viewPager.currentItem = savedInstanceState.getInt(CURRENT_ITEM_POS)
        }
    }

    fun updateCount(count: Int) {
        adapter.count = count
    }

    fun setCurrentPic(pos: Int) {
        viewPager.currentItem = pos
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
