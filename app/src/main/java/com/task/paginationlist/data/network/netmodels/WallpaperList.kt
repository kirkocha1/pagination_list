package com.task.paginationlist.data.network.netmodels

import com.google.gson.annotations.SerializedName

class WallpaperList {

    @SerializedName("response")
    var wallpapers: List<Wallpaper>? = null
}
