package com.task.paginationlist.data.db.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "wallpapers")
public class WallpaperDb {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "creation_date")
    private String creationDate;

    @ColumnInfo(name = "preview_image_url")
    private String imageUrl;

    @ColumnInfo(name = "thumb_image_url")
    private String thumbUrl;

    @ColumnInfo(name = "page_num")
    private int pageNum;

    @ColumnInfo(name = "page_position")
    private int pagePosition;

    @ColumnInfo(name = "server_id")
    private long serverId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setPagePosition(int pagePosition) {
        this.pagePosition = pagePosition;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getPagePosition() {
        return pagePosition;
    }

    public long getServerId() {
        return serverId;
    }

    public void setServerId(long serverId) {
        this.serverId = serverId;
    }
}
