package com.mohammad.data.store

import com.mohammad.data.repository.ProjectsDataStore
import javax.inject.Inject

class ProjectsDataStoreFactory @Inject constructor(
    private val mProjectsCacheDataStore: ProjectsCacheDataStore,
    private val mProjecteRemoteDataStore: ProjectsRemoteDataStore
) {

    open fun getDaraStore(mProjectsCached: Boolean, mCachedExpired: Boolean):ProjectsDataStore {
        return if(mProjectsCached && !mCachedExpired)
            mProjectsCacheDataStore
        else
            mProjecteRemoteDataStore
    }

    open fun getCacheDataStore():ProjectsDataStore{
        return mProjectsCacheDataStore
    }
}