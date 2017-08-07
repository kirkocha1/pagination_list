package com.task.paginationlist.presentation.piclist.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.task.paginationlist.PaginationListApplication;
import com.task.paginationlist.R;
import com.task.paginationlist.data.utils.ILoader;
import com.task.paginationlist.domain.interactors.WallpaperListInteractor;
import com.task.paginationlist.presentation.piclist.presenter.PicturePresenter;
import com.task.paginationlist.presentation.piclist.views.fragments.intefaces.IPictureView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PictureFragment extends MvpAppCompatFragment implements IPictureView {

    public static final String PAGE_NUM = "page_num";
    public static final String PAGE_POSITION = "page_position";


    public static PictureFragment createPictureFragment(Pair<Integer, Integer> pair) {
        PictureFragment fragment = new PictureFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(PAGE_NUM, pair.first);
        bundle.putInt(PAGE_POSITION, pair.second);
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R.id.full_picture)
    ImageView imageView;

    @Inject
    ILoader loader;

    @Inject
    WallpaperListInteractor interactor;

    @InjectPresenter
    public PicturePresenter presenter;

    @ProvidePresenter
    public PicturePresenter providePresenter() {
        return new PicturePresenter(interactor, getArguments().getInt(PAGE_NUM), getArguments().getInt(PAGE_POSITION));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        PaginationListApplication.getComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picture, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setImageUrl(String url) {
        loader.loadImage(url, imageView);
    }
}
