package com.mohammad.data

import com.mohammad.data.mapper.ProjectMapper
import com.mohammad.data.repository.ProjectsCache
import com.mohammad.data.store.ProjectsDataStoreFactory
import com.mohammad.domain.model.Project
import com.mohammad.domain.repository.ProjectsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class ProjectsDataRepository @Inject constructor(
    private val mMapper: ProjectMapper,
    private val mCache: ProjectsCache,
    private val mFactory: ProjectsDataStoreFactory
) : ProjectsRepository {

    override fun getProjects(): Observable<List<Project>> {
        return Observable.zip(mCache.areProjectsCached().toObservable(),
            mCache.isProjectsCacheExpired().toObservable(),
            BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { mAreCached, mIsExpired ->
                Pair(mAreCached, mIsExpired)
            })
            .flatMap {mFactory.getDaraStore(it.first,it.second).getProjects()}
            .flatMap{mProjects ->
                mFactory.getCacheDataStore()
                    .saveProjects(mProjects)
                    .andThen(Observable.just(mProjects))
            }
            .map { it ->
                it.map {
                    mMapper.mapFromEntity(it)
                }
            }
    }

    override fun bookmarkProject(mProjectId: String): Completable {
        return mFactory.getCacheDataStore().setProjectAsBookmarked(mProjectId)
    }

    override fun unBookmarkProject(mProjectId: String): Completable {
        return mFactory.getCacheDataStore().setProjectAsNotBookmarked(mProjectId)
    }

    override fun getBookmarkedProjects(): Observable<List<Project>> {
        return mFactory.getCacheDataStore().getBookmarkedProjects()
            .map {
                it.map {
                    mMapper.mapFromEntity(it)
                }
            }
    }
}