package com.mohammad.domian.interactor.bookmark

import com.mohammad.domain.executer.PostExecutionThread
import com.mohammad.domain.interactor.bookmark.GetBookmarkedProjects
import com.mohammad.domain.model.Project
import com.mohammad.domain.repository.ProjectsRepository
import com.mohammad.domian.test.ProjectDataFactory
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetBookmarkedProjectsTest {

    private lateinit var mGetBookmarkedProjectsTest: GetBookmarkedProjects
    @Mock
    lateinit var mProjectsRepository: ProjectsRepository
    @Mock
    lateinit var mPostExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mGetBookmarkedProjectsTest = GetBookmarkedProjects(mProjectsRepository, mPostExecutionThread)
    }

    @Test
    fun getBookmarkedProjectsCompletes() {
        stubGetProjects(Observable.just(ProjectDataFactory.makeProjectList(2)))
        val mTestObserver = mGetBookmarkedProjectsTest.buildUseCaseObservable().test()
        mTestObserver.assertComplete()
    }

    @Test
    fun getBookmarkedProjectsReturnsData() {
        val mProjects = ProjectDataFactory.makeProjectList(2)
        stubGetProjects(Observable.just(mProjects))
        val mTestObserver = mGetBookmarkedProjectsTest.buildUseCaseObservable().test()
        mTestObserver.assertValue(mProjects)
    }

    private fun stubGetProjects(mObservable: Observable<List<Project>>) {
        whenever(mProjectsRepository.getBookmarkedProjects())
            .thenReturn(mObservable)
    }
}