package com.task.paginationlist.presentation.piclist.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.Pair;

import com.task.paginationlist.presentation.piclist.views.fragments.PictureFragment;
import com.task.paginationlist.presentation.piclist.views.paginator.Config;

public class PicFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private int count;

    public PicFragmentPagerAdapter(FragmentManager fm, int count) {
        super(fm);
        this.count = count;
    }

    @Override
    public Fragment getItem(int position) {
        return PictureFragment.createPictureFragment(getPageAndPosionOfDbElement(position));
    }

    public void setCount(int count) {
        this.count = count;
        notifyDataSetChanged();
    }

    private Pair<Integer, Integer> getPageAndPosionOfDbElement(int pos) {
        int page = pos / Config.LIMIT + 1;
        int dbPosition = pos % Config.LIMIT;
        return new Pair<>(page, dbPosition);
    }

    @Override
    public int getCount() {
        return count;
    }
}
