package com.task.paginationlist.presentation.piclist.views.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.task.paginationlist.R;
import com.task.paginationlist.presentation.piclist.views.fragments.FullSizePicFragment;


public class FullSizeContainerActivity extends AppCompatActivity {
    public static final String POSITON_PIC = "position_pic";
    public static final String PIC_ID = "pic_id";
    public static final String PIC_COUNT = "pic_count";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_size_container);
        initUi();
    }

    private void initUi() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Fragment fragment = FullSizePicFragment.createFullSizePicFragment(getIntent().getIntExtra(POSITON_PIC, 0),
                getIntent().getIntExtra(PIC_ID, 0), getIntent().getIntExtra(PIC_COUNT, 0));
        getSupportFragmentManager().beginTransaction().replace(R.id.full_size_container, fragment).commit();
    }
}
