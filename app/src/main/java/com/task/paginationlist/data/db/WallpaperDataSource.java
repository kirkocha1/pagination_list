package com.task.paginationlist.data.db;

import com.task.paginationlist.data.db.dao.WallpaperDao;
import com.task.paginationlist.data.db.models.WallpaperDb;

import java.util.List;


public class WallpaperDataSource {

    private WallpaperDao wallpaperDao;

    public WallpaperDataSource(WallpaperDao wallpaperDao) {
        this.wallpaperDao = wallpaperDao;
    }

    public List<WallpaperDb> getItems() {
        return wallpaperDao.getWallpapers();
    }

    public List<WallpaperDb> getItems(int page) {
        return wallpaperDao.getWallpapersForPage(page);
    }

    public WallpaperDb getItem(int id) {
        return wallpaperDao.getWallpaper(id);
    }

    public WallpaperDb getItem(int page, int position) {
        return wallpaperDao.getWallpaper(page, position);
    }

    public void putItems(List<WallpaperDb> wallpapers) {
        wallpaperDao.insertWallpapers(wallpapers);
    }

    public void deleteItems() {
        wallpaperDao.deleteWallpapers();
    }

    public void deleteItems(List<WallpaperDb> wallpapers) {

    }
}
