package com.mohammad.data.repository

import com.mohammad.data.model.ProjectEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface ProjectsCache {

    fun clearProjects(): Completable

    fun saveProjects(mProjects: List<ProjectEntity>): Completable

    fun getProjects(): Observable<List<ProjectEntity>>

    fun getBookmarkedProjects(): Observable<List<ProjectEntity>>

    fun setProjectAsBookmark(mProjectId: String): Completable

    fun setProjectAsNotBookmark(mProjectId: String): Completable

    fun areProjectsCached(): Single<Boolean>

    fun setLastCacheTime(mLastCacheTime: Long): Completable

    fun isProjectsCacheExpired(): Single<Boolean>
}
