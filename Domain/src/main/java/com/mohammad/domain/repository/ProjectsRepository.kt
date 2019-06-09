package com.mohammad.domain.repository

import com.mohammad.domain.model.Project
import io.reactivex.Completable
import io.reactivex.Observable

interface ProjectsRepository {

    fun getProjects(): Observable<List<Project>>

    fun bookmarkProject(mProjectId: String): Completable

    fun unBookmarkProject(mProjectId: String): Completable

    fun getBookmarkedProjects(): Observable<List<Project>>
}