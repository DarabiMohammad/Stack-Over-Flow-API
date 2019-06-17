package com.mohammad.domian.interactor.bookmark

import com.mohammad.domain.executer.PostExecutionThread
import com.mohammad.domain.interactor.bookmark.UnBookmarkProject
import com.mohammad.domain.repository.ProjectsRepository
import com.mohammad.domian.test.ProjectDataFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UnBookmarkProjectTest {

    private lateinit var mUnBookmarkProject: UnBookmarkProject
    @Mock
    lateinit var mProjectRepository: ProjectsRepository
    @Mock
    lateinit var mPostExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mUnBookmarkProject = UnBookmarkProject(mProjectRepository, mPostExecutionThread)
    }

    @Test
    fun unBookmarkProjectCompletes() {
        stubUnBookmarkProject(Completable.complete())
        val mTestObserver = mUnBookmarkProject.buildUseCaseCompletable(
            UnBookmarkProject.Params.forProject(ProjectDataFactory.randomUuid())
        ).test()
        mTestObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun unBookmarkProjectThrowsException() {
        mUnBookmarkProject.buildUseCaseCompletable().test()
    }

    fun stubUnBookmarkProject(mCompletable: Completable) {
        whenever(mProjectRepository.unBookmarkProject(any()))
            .thenReturn(mCompletable)
    }
}