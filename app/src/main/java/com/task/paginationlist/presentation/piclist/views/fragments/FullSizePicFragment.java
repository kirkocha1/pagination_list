package com.task.paginationlist.presentation.piclist.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.task.paginationlist.R;
import com.task.paginationlist.presentation.piclist.views.adapters.PicFragmentPagerAdapter;
import com.task.paginationlist.presentation.piclist.views.paginator.Config;
import com.task.paginationlist.presentation.piclist.views.utils.UtilsHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FullSizePicFragment extends MvpAppCompatFragment {
    public static final String CURRENT_ITEM_POS = "current_item_pos";
    public static final String CURRENT_ITEM_COUNT = "current_item_count";
    public static final String PIC_POSITION = "pic_position";
    public static final String PICS_COUNT = "pics_count";
    public static final String PIC_ID = "pic_id";

    @BindView(R.id.view_pager)
    ViewPager pager;

    PicFragmentPagerAdapter adapter;

    public static FullSizePicFragment createFullSizePicFragment(int pos, int id, int count) {
        FullSizePicFragment fragment = new FullSizePicFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(PIC_POSITION, pos);
        bundle.putInt(PIC_ID, id);
        bundle.putInt(PICS_COUNT, count);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_full_pic, container, false);
        ButterKnife.bind(this, view);
        initUi();
        return view;
    }

    private void initUi() {
        adapter = new PicFragmentPagerAdapter(getFragmentManager(), getArguments() != null ? getArguments().getInt(PICS_COUNT) : Config.LIMIT);
        pager.setAdapter(adapter);
        pager.setCurrentItem(getArguments() != null ? getArguments().getInt(PIC_POSITION) : 0);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_ITEM_POS, pager.getCurrentItem());
        outState.putInt(CURRENT_ITEM_COUNT, adapter.getCount());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null && pager != null) {
            adapter.setCount(savedInstanceState.getInt(CURRENT_ITEM_COUNT));
            pager.setCurrentItem(savedInstanceState.getInt(CURRENT_ITEM_POS));
        }
    }

    public void updateCount(int count) {
        adapter.setCount(count);
    }

    public void setCurrentPic(int pos) {
        pager.setCurrentItem(pos);
    }
}
