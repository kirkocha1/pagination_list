package com.task.paginationlist.presentation.piclist.views.activities;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.task.paginationlist.R;
import com.task.paginationlist.presentation.piclist.views.activities.interfaces.IFullSizeViewCreator;
import com.task.paginationlist.presentation.piclist.views.fragments.FullSizePicFragment;

public class MainActivity extends MvpAppCompatActivity implements IFullSizeViewCreator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void createFullSizePicFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.full_part_container, new FullSizePicFragment(), getString(R.string.full_size_fragment_tag))
                .commit();

    }
}
