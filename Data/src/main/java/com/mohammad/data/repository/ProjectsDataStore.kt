package com.mohammad.data.repository

import com.mohammad.data.model.ProjectEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface ProjectsDataStore {

    fun getProjects(): Observable<List<ProjectEntity>>

    fun saveProjects(mProjects: List<ProjectEntity>):Completable

    fun clearProjects():Completable

    fun getBookmarkedProjects(): Observable<List<ProjectEntity>>

    fun setProjectAsBookmarked(mProjectId: String): Completable

    fun setProjectAsNotBookmarked(mProjectId: String): Completable
}