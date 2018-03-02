package com.task.paginationlist.presentation.piclist.views.fragments

import android.os.Bundle
import android.support.v4.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.task.paginationlist.PaginationListApplication
import com.task.paginationlist.R
import com.task.paginationlist.data.utils.ILoader
import com.task.paginationlist.domain.interactors.WallpaperListInteractor
import com.task.paginationlist.presentation.piclist.presenter.PicturePresenter
import com.task.paginationlist.presentation.piclist.views.fragments.intefaces.IPictureView
import kotlinx.android.synthetic.main.fragment_picture.*
import javax.inject.Inject


class PictureFragment : MvpAppCompatFragment(), IPictureView {

    @Inject
    lateinit var loader: ILoader

    @Inject
    lateinit var interactor: WallpaperListInteractor

    @InjectPresenter
    lateinit var presenter: PicturePresenter

    @ProvidePresenter
    fun providePresenter(): PicturePresenter {
        return PicturePresenter(interactor!!, arguments.getInt(PAGE_NUM), arguments.getInt(PAGE_POSITION))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        PaginationListApplication.component?.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_picture, container, false)
        return view
    }

    override fun setImageUrl(url: String) {
        loader!!.loadImage(url, fullPicture)
    }

    companion object {

        val PAGE_NUM = "page_num"
        val PAGE_POSITION = "page_position"


        fun createPictureFragment(pair: Pair<Int, Int>): PictureFragment {
            val fragment = PictureFragment()
            val bundle = Bundle()
            bundle.putInt(PAGE_NUM, pair.first)
            bundle.putInt(PAGE_POSITION, pair.second)
            fragment.arguments = bundle
            return fragment
        }
    }
}
