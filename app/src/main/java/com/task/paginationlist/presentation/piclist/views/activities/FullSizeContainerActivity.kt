package com.task.paginationlist.presentation.piclist.views.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

import com.task.paginationlist.R
import com.task.paginationlist.presentation.piclist.views.fragments.FullSizePicFragment


class FullSizeContainerActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_size_container)
        initUi(savedInstanceState != null)
    }

    private fun initUi(isRestored: Boolean) {
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        if (!isRestored) {
            val fragment = FullSizePicFragment.createFullSizePicFragment(intent.getIntExtra(POSITON_PIC, 0),
                    intent.getIntExtra(PIC_ID, 0), intent.getIntExtra(PIC_COUNT, 0))
            supportFragmentManager.beginTransaction().replace(R.id.full_size_container, fragment).commit()
        }
    }

    companion object {
        val POSITON_PIC = "position_pic"
        val PIC_ID = "pic_id"
        val PIC_COUNT = "pic_count"
    }
}
