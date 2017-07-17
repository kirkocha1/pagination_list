package com.task.paginationlist.presentation.piclist.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.task.paginationlist.PaginationListApplication;
import com.task.paginationlist.R;
import com.task.paginationlist.data.db.models.WallpaperDb;
import com.task.paginationlist.data.utils.ILoader;
import com.task.paginationlist.presentation.piclist.interfaces.OnPickClickListener;
import com.task.paginationlist.presentation.piclist.views.activities.interfaces.IPaginatorAdapter;
import com.task.paginationlist.presentation.piclist.views.adapters.holders.LoadingHolder;
import com.task.paginationlist.presentation.piclist.views.adapters.holders.PicViewHolder;
import com.task.paginationlist.presentation.piclist.views.paginator.Config;

import java.text.ParseException;
import java.util.List;

public class PicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements IPaginatorAdapter {
    private static final String TAG = "PicAdapter";
    public static final int ITEM_TYPE = 1;
    public static final int FOOTER_TYPE = 0;

    private boolean isSuccessful = true;

    private View.OnClickListener reload;
    private OnPickClickListener onPicClick;


    ILoader loader;

    private List<WallpaperDb> wallpapers;

    public PicAdapter() {
        loader = PaginationListApplication.getComponent().getLoader();
    }

    public void addData(List<WallpaperDb> models, boolean isRefreshing) {
        if (isRefreshing) {
            this.wallpapers = null;
        }
        if (this.wallpapers == null) {
            this.wallpapers = models;
        } else {
            this.wallpapers.addAll(models);
        }
        isSuccessful = true;
        notifyDataSetChanged();
    }

    public void setReload(View.OnClickListener listener) {
        this.reload = listener;
    }

    public void setOnPicClick(OnPickClickListener onPicClick) {
        this.onPicClick = onPicClick;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
        notifyItemChanged(getItemCount() - 1);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isFooter(position)) {
            LoadingHolder loadingHolder = ((LoadingHolder) holder);
            loadingHolder.loaderView.setReloadClick(reload);
            if (!isSuccessful) {
                loadingHolder.loaderView.showError();
            } else {
                ((LoadingHolder) holder).loaderView.showBar();
            }
        } else {
            PicViewHolder picHolder = (PicViewHolder) holder;
            loader.loadImage(wallpapers.get(position).getThumbUrl(), picHolder.image);
            picHolder.image.setOnClickListener(v -> {
                if (onPicClick != null) {
                    onPicClick.onPickClick(v, position, wallpapers.get(position).getId(), wallpapers.size());
                }
            });
            String text = "";
            try {
                text = Config.formatDate(wallpapers.get(position).getCreationDate());
            } catch (ParseException e) {
                Log.e(TAG, e.getMessage());
            }
            picHolder.date.setText(text);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return viewType == FOOTER_TYPE ? new LoadingHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_item, parent, false))
                : new PicViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pic_list_item, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return isFooter(position) ? FOOTER_TYPE : ITEM_TYPE;
    }

    private boolean isFooter(int position) {
        return position == getItemCount() - 1;
    }

    public int getRealItemCount() {
        return getItemCount() - 1;
    }

    @Override
    public int getItemCount() {
        return wallpapers != null ? wallpapers.size() + 1 : 1;
    }
}
