package com.task.paginationlist.repositories;


public class RepoResult {

    public boolean wasDeleted = false;
    public boolean isUpdated = false;
    public long id;

    public RepoResult(boolean isUpdated, long id) {
        this.isUpdated = isUpdated;
        this.id = id;
    }

    public RepoResult(boolean wasDeleted) {
        this.wasDeleted = true;
    }

}
