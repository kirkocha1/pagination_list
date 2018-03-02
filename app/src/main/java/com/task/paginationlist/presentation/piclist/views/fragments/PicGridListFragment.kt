package com.task.paginationlist.presentation.piclist.views.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.task.paginationlist.PaginationListApplication
import com.task.paginationlist.R
import com.task.paginationlist.data.db.models.WallpaperDb
import com.task.paginationlist.domain.interactors.WallpaperListInteractor
import com.task.paginationlist.presentation.piclist.presenter.PicListPresenter
import com.task.paginationlist.presentation.piclist.views.activities.FullSizeContainerActivity
import com.task.paginationlist.presentation.piclist.views.activities.interfaces.IPicListView
import com.task.paginationlist.presentation.piclist.views.adapters.PicAdapter
import com.task.paginationlist.presentation.piclist.views.paginator.Config
import com.task.paginationlist.presentation.piclist.views.paginator.PaginatorList
import com.task.paginationlist.presentation.piclist.views.utils.ErrorHandler
import com.task.paginationlist.presentation.piclist.views.utils.UtilsHelper

import javax.inject.Inject

import butterknife.BindView
import butterknife.ButterKnife
import io.reactivex.ObservableTransformer

import com.task.paginationlist.presentation.piclist.views.activities.FullSizeContainerActivity.Companion.PIC_COUNT
import com.task.paginationlist.presentation.piclist.views.activities.FullSizeContainerActivity.Companion.PIC_ID
import com.task.paginationlist.presentation.piclist.views.activities.FullSizeContainerActivity.Companion.POSITON_PIC


class PicGridListFragment : MvpAppCompatFragment(), IPicListView {

    @InjectPresenter
    internal var presenter: PicListPresenter? = null

    @Inject
    internal var interactor: WallpaperListInteractor? = null

    @Inject
    internal var transformer: ObservableTransformer<List<WallpaperDb>, List<WallpaperDb>>? = null

    @Inject
    internal var handler: ErrorHandler? = null

    @BindView(R.id.paginator_list)
    internal var paginatorList: PaginatorList? = null

    private var adapter: PicAdapter? = null

    @ProvidePresenter
    internal fun providePresenter(): PicListPresenter {
        return PicListPresenter(interactor!!, transformer!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        PaginationListApplication.getComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.picture_list_fragment, null)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PicAdapter()
        adapter!!.setReload { v -> presenter!!.loadMoreData(adapter!!.itemCount / Config.LIMIT + 1) }
        paginatorList!!.setAdapter(adapter)
        paginatorList!!.setLoadMoreListener { page -> presenter!!.loadMoreData(page) }
        val manager = GridLayoutManager(context, Config.GRID_ROWS_COUNT)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                when (adapter!!.getItemViewType(position)) {
                    PicAdapter.ITEM_TYPE -> return 1
                    PicAdapter.FOOTER_TYPE -> return Config.GRID_ROWS_COUNT
                    else -> return -1
                }
            }
        }
        paginatorList!!.setManager(manager)
        adapter!!.setOnPicClick({ v, pos, id, count ->
            if (!UtilsHelper.isTablet(context)) {
                val intent = Intent(context, FullSizeContainerActivity::class.java)
                intent.putExtra(Companion.getPOSITON_PIC(), pos)
                intent.putExtra(Companion.getPIC_ID(), id)
                intent.putExtra(Companion.getPIC_COUNT(), count)
                startActivity(intent)
            } else {
                setFullSizePic(pos)
            }
        })
    }

    override fun invalidateList(wallpapers: MutableList<WallpaperDb>?) {
        val isAllLoaded = wallpapers == null || wallpapers.size < Config.LIMIT
        paginatorList!!.setAllLoaded(isAllLoaded)
        paginatorList!!.post {
            adapter!!.setFooterVisibility(isAllLoaded)
            adapter!!.addData(wallpapers, paginatorList!!.isRefreshing)
            paginatorList!!.onCompleteLoad()
            presenter!!.handleOnPostEvent(adapter!!.itemCount - 1)
        }
    }

    override fun showError(error: String) {
        adapter!!.setSuccessful(false)
        adapter!!.setFooterVisibility(false)
        paginatorList!!.onErrorLoad()
        handler!!.handlerErrorMessage(context, error)
    }

    override fun setFullSizePic(pos: Int) {
        val fragment = fragmentManager.findFragmentByTag(getString(R.string.full_size_fragment_tag)) as FullSizePicFragment
        fragment?.setCurrentPic(pos)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putInt(RESTORE_LAST_VISIBLE_ITEM_POS, paginatorList!!.lastListItemPosition)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            presenter!!.restoreListState(savedInstanceState.getInt(RESTORE_LAST_VISIBLE_ITEM_POS))
        }
    }

    override fun restoreListView(restoredData: MutableList<WallpaperDb>, lastItemPosition: Int) {
        paginatorList!!.setLastVisiblePosition(lastItemPosition)
        invalidateList(restoredData)
    }

    override fun updateFullSizeCount(count: Int) {
        if (UtilsHelper.isTablet(context)) {
            val fragment = fragmentManager.findFragmentByTag(getString(R.string.full_size_fragment_tag)) as FullSizePicFragment
            fragment?.updateCount(count)
        }
    }

    companion object {

        val RESTORE_LAST_VISIBLE_ITEM_POS = "restore_last_visible_item_pos"
    }
}
