package com.task.paginationlist.data.utils;

import com.task.paginationlist.data.db.models.WallpaperDb;
import com.task.paginationlist.data.network.netmodels.Wallpaper;

/**
 * Created by kirill on 17.07.17.
 */

public class WallpaperMapper {

    public WallpaperDb map(Wallpaper wallpaper) {
        WallpaperDb result = new WallpaperDb();
        result.setServerId(wallpaper.id);
        result.setCreationDate(wallpaper.created);
        result.setImageUrl(wallpaper.imageDetails.preview.url);
        result.setThumbUrl(wallpaper.imageDetails.thumb.url);
        return result;
    }

}
