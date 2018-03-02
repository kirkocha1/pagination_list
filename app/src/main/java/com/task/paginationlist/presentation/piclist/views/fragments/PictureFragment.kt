package com.task.paginationlist.presentation.piclist.views.fragments

import android.os.Bundle
import android.support.v4.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.task.paginationlist.PaginationListApplication
import com.task.paginationlist.R
import com.task.paginationlist.data.utils.ILoader
import com.task.paginationlist.domain.interactors.WallpaperListInteractor
import com.task.paginationlist.presentation.piclist.presenter.PicturePresenter
import com.task.paginationlist.presentation.piclist.views.fragments.intefaces.IPictureView

import javax.inject.Inject

import butterknife.BindView
import butterknife.ButterKnife


class PictureFragment : MvpAppCompatFragment(), IPictureView {

    @BindView(R.id.full_picture)
    internal var imageView: ImageView? = null

    @Inject
    internal var loader: ILoader? = null

    @Inject
    internal var interactor: WallpaperListInteractor? = null

    @InjectPresenter
    var presenter: PicturePresenter? = null

    @ProvidePresenter
    fun providePresenter(): PicturePresenter {
        return PicturePresenter(interactor!!, arguments.getInt(PAGE_NUM), arguments.getInt(PAGE_POSITION))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        PaginationListApplication.getComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_picture, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun setImageUrl(url: String) {
        loader!!.loadImage(url, imageView!!)
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
