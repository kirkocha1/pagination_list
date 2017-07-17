package com.task.paginationlist.data.network.netmodels;

import com.google.gson.annotations.SerializedName;
import com.task.paginationlist.data.db.models.WallpaperDb;


public class Wallpaper {

    @SerializedName("id")
    public long id;

    @SerializedName("bytes")
    public long size;

    @SerializedName("created_at")
    public String created;

    @SerializedName("image")
    public ImageDetails imageDetails;

    public WallpaperDb toWallpaperDb() {
        WallpaperDb result = new WallpaperDb();
        result.setServerId(id);
        result.setCreationDate(created);
        result.setImageUrl(imageDetails.preview.url);
        result.setThumbUrl(imageDetails.thumb.url);
        return result;
    }
}


