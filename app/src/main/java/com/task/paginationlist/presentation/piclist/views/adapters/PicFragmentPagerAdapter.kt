package com.task.paginationlist.presentation.piclist.views.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.util.Pair

import com.task.paginationlist.presentation.piclist.views.fragments.PictureFragment
import com.task.paginationlist.presentation.piclist.views.paginator.Config

class PicFragmentPagerAdapter(fm: FragmentManager, private var count: Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return PictureFragment.createPictureFragment(getPageAndPosionOfDbElement(position))
    }

    fun setCount(count: Int) {
        this.count = count
        notifyDataSetChanged()
    }

    private fun getPageAndPosionOfDbElement(pos: Int): Pair<Int, Int> {
        val page = pos / Config.LIMIT + 1
        val dbPosition = pos % Config.LIMIT
        return Pair(page, dbPosition)
    }

    override fun getCount(): Int {
        return count
    }
}
