package com.mohammad.data.store

import com.mohammad.data.model.ProjectEntity
import com.mohammad.data.repository.ProjectsCache
import com.mohammad.data.repository.ProjectsDataStore
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsCacheDataStore @Inject constructor(
    private val mProjectsCache: ProjectsCache
) : ProjectsDataStore {

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return mProjectsCache.getProjects()
    }

    override fun clearProjects(): Completable {
        return mProjectsCache.clearProjects()
    }

    override fun saveProjects(mProjects: List<ProjectEntity>): Completable {
        return mProjectsCache.saveProjects(mProjects)
            .andThen(mProjectsCache.setLastCacheTime(System.currentTimeMillis()))
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        return mProjectsCache.getBookmarkedProjects()
    }

    override fun setProjectAsBookmarked(mProjectId: String): Completable {
        return mProjectsCache.setProjectAsBookmark(mProjectId)
    }

    override fun setProjectAsNotBookmarked(mProjectId: String): Completable {
        return mProjectsCache.setProjectAsNotBookmark(mProjectId)
    }
}