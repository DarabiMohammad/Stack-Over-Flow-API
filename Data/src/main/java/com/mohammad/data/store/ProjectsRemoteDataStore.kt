package com.mohammad.data.store

import com.mohammad.data.model.ProjectEntity
import com.mohammad.data.repository.ProjectsDataStore
import com.mohammad.data.repository.ProjectsRemote
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsRemoteDataStore @Inject constructor(
    private val mProjectsRemote: ProjectsRemote
) : ProjectsDataStore {

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return mProjectsRemote.getProjects()
    }

    override fun saveProjects(mProjects: List<ProjectEntity>): Completable {
        throw UnsupportedOperationException("Saving Projects Isnt Supported Here . . .")
    }

    override fun clearProjects(): Completable {
        throw UnsupportedOperationException("Clearing Projects Isnt Supported Here . . .")
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        throw UnsupportedOperationException("Getting Projects Isnt Supported Here . . .")
    }

    override fun setProjectAsBookmarked(mProjectId: String): Completable {
        throw UnsupportedOperationException("Setting Projects Isnt Supported Here . . .")
    }

    override fun setProjectAsNotBookmarked(mProjectId: String): Completable {
        throw UnsupportedOperationException("setting Projects Isnt Supported Here . . .")
    }
}