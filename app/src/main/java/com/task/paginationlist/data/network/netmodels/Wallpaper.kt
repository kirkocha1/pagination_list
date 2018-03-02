package com.task.paginationlist.data.network.netmodels

import com.google.gson.annotations.SerializedName


class Wallpaper {

    @SerializedName("id")
    var id: Long = 0

    @SerializedName("bytes")
    var size: Long = 0

    @SerializedName("created_at")
    var created: String? = null

    @SerializedName("image")
    var imageDetails: ImageDetails? = null

}


