package com.task.paginationlist.data.network.netmodels

import com.google.gson.annotations.SerializedName


class ImageDetails {

    @SerializedName("thumb")
    var thumb: BaseDetails? = null

    @SerializedName("preview")
    var preview: BaseDetails? = null
}
