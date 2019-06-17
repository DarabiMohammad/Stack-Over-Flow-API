package com.mohammad.data.test.store

import com.mohammad.data.store.ProjectsCacheDataStore
import com.mohammad.data.store.ProjectsDataStoreFactory
import com.mohammad.data.store.ProjectsRemoteDataStore
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test
import kotlin.test.assertEquals

class ProjectsDataStoreFactoryTest {

    private val mCacheStore = mock<ProjectsCacheDataStore>()
    private val mRemoteStore = mock<ProjectsRemoteDataStore>()
    private val mFactory = ProjectsDataStoreFactory(mCacheStore,mRemoteStore)

    @Test
    fun getDataStoreReturnsRemoteStoreWhenCacheExpired(){
        assertEquals(mRemoteStore,mFactory.getDaraStore(mProjectsCached = true, mCachedExpired = true))
    }

    @Test
    fun getDataStoreReturnsRemoteStoreWhenCacheProjectNotCached(){
        assertEquals(mRemoteStore,mFactory.getDaraStore(mProjectsCached = false, mCachedExpired = false))
    }
}