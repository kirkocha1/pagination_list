package com.task.paginationlist.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.task.paginationlist.data.db.models.WallpaperDb;

import java.util.List;


@Dao
public interface WallpaperDao {

    @Query("SELECT * FROM wallpapers")
    List<WallpaperDb> getWallpapers();

    @Query("SELECT * FROM wallpapers WHERE page_num = :page")
    List<WallpaperDb> getWallpapersForPage(int page);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWallpapers(List<WallpaperDb> wallpapers);

    @Query("DELETE FROM wallpapers")
    void deleteWallpapers();

    @Query("SELECT * FROM wallpapers WHERE id = :id LIMIT 1")
    WallpaperDb getWallpaper(int id);

    @Query("SELECT * FROM wallpapers WHERE page_num = :page AND page_position = :position LIMIT 1")
    WallpaperDb getWallpaper(int page, int position);

}
