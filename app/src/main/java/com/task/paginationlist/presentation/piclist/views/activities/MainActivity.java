package com.task.paginationlist.presentation.piclist.views.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.task.paginationlist.R;
import com.task.paginationlist.presentation.piclist.views.fragments.FullSizePicFragment;
import com.task.paginationlist.presentation.piclist.views.fragments.PicGridListFragment;
import com.task.paginationlist.presentation.piclist.views.utils.UtilsHelper;

public class MainActivity extends MvpAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            inflateWithFragments();
        }
    }

    private void inflateWithFragments() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .replace(R.id.list_pic_container, new PicGridListFragment(), getString(R.string.grid_fragment_tag));
        if (UtilsHelper.isTablet(this)) {
            transaction.replace(R.id.full_part_container, new FullSizePicFragment(), getString(R.string.full_size_fragment_tag));
        }
        transaction.commit();
    }
}
