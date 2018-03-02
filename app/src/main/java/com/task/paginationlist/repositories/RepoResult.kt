package com.task.paginationlist.repositories


class RepoResult {

    var wasDeleted = false
    var isUpdated = false
    var id: Long = 0

    constructor(isUpdated: Boolean, id: Long) {
        this.isUpdated = isUpdated
        this.id = id
    }

    constructor(wasDeleted: Boolean) {
        this.wasDeleted = true
    }

}
