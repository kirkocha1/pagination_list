package com.task.paginationlist.data.utils

import com.task.paginationlist.data.db.models.WallpaperDb
import com.task.paginationlist.data.network.netmodels.Wallpaper

/**
 * Created by kirill on 17.07.17.
 */

class WallpaperMapper {

    fun map(wallpaper: Wallpaper): WallpaperDb {
        val result = WallpaperDb()
        result.serverId = wallpaper.id
        result.creationDate = wallpaper.created
        result.imageUrl = wallpaper.imageDetails!!.preview!!.url
        result.thumbUrl = wallpaper.imageDetails!!.thumb!!.url
        return result
    }

}
