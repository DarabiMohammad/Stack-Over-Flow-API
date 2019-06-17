package com.mohammad.domian.interactor.browser

import com.mohammad.domain.executer.PostExecutionThread
import com.mohammad.domain.interactor.browse.GetProjects
import com.mohammad.domain.model.Project
import com.mohammad.domain.repository.ProjectsRepository
import com.mohammad.domian.test.ProjectDataFactory
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetProjectsTest {

    private lateinit var mGetProjects: GetProjects
    @Mock
    lateinit var mProjectsRepository: ProjectsRepository
    @Mock
    lateinit var mPostExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mGetProjects = GetProjects(mProjectsRepository, mPostExecutionThread)
    }

    @Test
    fun getProjectsCompletes() {
        stubGetProjects(Observable.just(ProjectDataFactory.makeProjectList(2)))
        val mTestObserver = mGetProjects.buildUseCaseObservable().test()
        mTestObserver.assertComplete()
    }

    @Test
    fun getProjectsReturnsData() {
        val mProjects = ProjectDataFactory.makeProjectList(2)
        stubGetProjects(Observable.just(mProjects))
        val mTestObserver = mGetProjects.buildUseCaseObservable().test()
        mTestObserver.assertValue(mProjects)
    }

    private fun stubGetProjects(mObservable: Observable<List<Project>>) {
        whenever(mProjectsRepository.getProjects())
            .thenReturn(mObservable)
    }
}