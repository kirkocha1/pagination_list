package com.task.paginationlist.data.network.netmodels;

import com.google.gson.annotations.SerializedName;


public class ImageDetails {

    @SerializedName("thumb")
    public BaseDetails thumb;

    @SerializedName("preview")
    public BaseDetails preview;
}
