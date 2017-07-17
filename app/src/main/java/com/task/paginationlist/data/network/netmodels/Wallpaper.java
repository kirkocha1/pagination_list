package com.task.paginationlist.data.network.netmodels;

import com.google.gson.annotations.SerializedName;


public class Wallpaper {

    @SerializedName("id")
    public long id;

    @SerializedName("bytes")
    public long size;

    @SerializedName("created_at")
    public String created;

    @SerializedName("image")
    public ImageDetails imageDetails;

}


