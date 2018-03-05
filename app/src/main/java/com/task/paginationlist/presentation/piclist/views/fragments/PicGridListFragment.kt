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
import com.task.paginationlist.presentation.piclist.interfaces.OnPickClickListener
import com.task.paginationlist.presentation.piclist.presenter.PicListPresenter
import com.task.paginationlist.presentation.piclist.views.activities.FullSizeContainerActivity
import com.task.paginationlist.presentation.piclist.views.activities.FullSizeContainerActivity.Companion.PIC_COUNT
import com.task.paginationlist.presentation.piclist.views.activities.FullSizeContainerActivity.Companion.PIC_ID
import com.task.paginationlist.presentation.piclist.views.activities.FullSizeContainerActivity.Companion.POSITON_PIC
import com.task.paginationlist.presentation.piclist.views.activities.interfaces.IPicListView
import com.task.paginationlist.presentation.piclist.views.adapters.PicAdapter
import com.task.paginationlist.presentation.piclist.views.paginator.Config
import com.task.paginationlist.presentation.piclist.views.paginator.PaginatorList
import com.task.paginationlist.presentation.piclist.views.utils.ErrorHandler
import com.task.paginationlist.presentation.piclist.views.utils.UtilsHelper
import io.reactivex.ObservableTransformer
import kotlinx.android.synthetic.main.picture_list_fragment.*
import javax.inject.Inject


class PicGridListFragment : MvpAppCompatFragment(), IPicListView {

    @InjectPresenter
    lateinit var presenter: PicListPresenter

    @Inject
    lateinit var interactor: WallpaperListInteractor

    @Inject
    lateinit var transformer: ObservableTransformer<MutableList<WallpaperDb>, MutableList<WallpaperDb>>

    @Inject
    lateinit var handler: ErrorHandler

    private var adapter: PicAdapter? = null

    @ProvidePresenter
    fun providePresenter(): PicListPresenter {
        return PicListPresenter(interactor!!, transformer!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        PaginationListApplication.component?.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.picture_list_fragment, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PicAdapter()
        adapter?.setReload(View.OnClickListener { v -> presenter.loadMoreData(adapter!!.itemCount / Config.LIMIT + 1) })
        adapter?.let { paginatorList.setAdapter(it) }
        paginatorList.setLoadMoreListener(
                object : PaginatorList.OnLoadMoreListener {
                    override fun loadMoreData(page: Int) {
                        presenter.loadMoreData(page)
                    }
                }
        )
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
        paginatorList.setManager(manager)
        adapter?.setOnPicClick(object : OnPickClickListener {
            override fun onPickClick(v: View, position: Int, id: Int, size: Int) {
                if (!UtilsHelper.isTablet(context)) {
                    val intent = Intent(context, FullSizeContainerActivity::class.java)
                    intent.putExtra(POSITON_PIC, position)
                    intent.putExtra(PIC_ID, id)
                    intent.putExtra(PIC_COUNT, size)
                    startActivity(intent)
                } else {
                    setFullSizePic(position)
                }
            }
        })
    }

    override fun invalidateList(wallpapers: MutableList<WallpaperDb>?) {
        val isAllLoaded = wallpapers == null || wallpapers.size < Config.LIMIT
        paginatorList.setAllLoaded(isAllLoaded)
        paginatorList.post {
            adapter?.setFooterVisibility(isAllLoaded)
            adapter?.addData(wallpapers, paginatorList.isRefreshing())
            paginatorList.onCompleteLoad()
            adapter?.let {
                presenter.handleOnPostEvent(it.itemCount - 1)
            }
        }
    }

    override fun restoreListView(restoredData: MutableList<WallpaperDb>, pos: Int) {
        paginatorList.setLastVisiblePosition(pos)
        invalidateList(restoredData)
    }


    override fun showError(error: String) {
        adapter?.setSuccessful(false)
        adapter?.setFooterVisibility(false)
        paginatorList.onErrorLoad()
        handler.handlerErrorMessage(context, error)
    }

    override fun setFullSizePic(pos: Int) {
        val fragment = fragmentManager.findFragmentByTag(getString(R.string.full_size_fragment_tag)) as FullSizePicFragment
        fragment.setCurrentPic(pos)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(RESTORE_LAST_VISIBLE_ITEM_POS, paginatorList.lastListItemPosition())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            presenter.restoreListState(savedInstanceState.getInt(RESTORE_LAST_VISIBLE_ITEM_POS))
        }
    }

    override fun updateFullSizeCount(count: Int) {
        if (UtilsHelper.isTablet(context)) {
            val fragment = fragmentManager.findFragmentByTag(getString(R.string.full_size_fragment_tag)) as FullSizePicFragment
            fragment.updateCount(count)
        }
    }

    companion object {
        val RESTORE_LAST_VISIBLE_ITEM_POS = "restore_last_visible_item_pos"
    }
}
