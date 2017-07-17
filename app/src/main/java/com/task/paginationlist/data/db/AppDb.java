package com.task.paginationlist.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.task.paginationlist.data.db.dao.WallpaperDao;
import com.task.paginationlist.data.db.models.WallpaperDb;


@Database(entities = {WallpaperDb.class}, version = 2)
public abstract class AppDb extends RoomDatabase {
    public abstract WallpaperDao getWallpaperDao();
}
