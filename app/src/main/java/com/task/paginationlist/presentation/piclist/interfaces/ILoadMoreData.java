package com.task.paginationlist.presentation.piclist.interfaces;


public interface ILoadMoreData {
    void loadMoreData(int page);

    void handleOnPostEvent(int count);
}
