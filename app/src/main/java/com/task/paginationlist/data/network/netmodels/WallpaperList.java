package com.task.paginationlist.data.network.netmodels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WallpaperList {

    @SerializedName("response")
    public List<Wallpaper> wallpapers;
}
