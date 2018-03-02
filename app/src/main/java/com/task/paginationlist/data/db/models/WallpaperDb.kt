package com.task.paginationlist.data.db.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "wallpapers")
class WallpaperDb {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "creation_date")
    var creationDate: String? = null

    @ColumnInfo(name = "preview_image_url")
    var imageUrl: String? = null

    @ColumnInfo(name = "thumb_image_url")
    var thumbUrl: String? = null

    @ColumnInfo(name = "page_num")
    var pageNum: Int = 0

    @ColumnInfo(name = "page_position")
    var pagePosition: Int = 0

    @ColumnInfo(name = "server_id")
    var serverId: Long = 0
}
