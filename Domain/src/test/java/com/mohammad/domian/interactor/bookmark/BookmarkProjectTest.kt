package com.mohammad.domian.interactor.bookmark

import com.mohammad.domain.executer.PostExecutionThread
import com.mohammad.domain.interactor.bookmark.BookmarkProject
import com.mohammad.domain.repository.ProjectsRepository
import com.mohammad.domian.test.ProjectDataFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class BookmarkProjectTest {

    private lateinit var mBookmarkProject: BookmarkProject
    @Mock
    lateinit var mProjectRepository: ProjectsRepository
    @Mock
    lateinit var mPostExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mBookmarkProject = BookmarkProject(mProjectRepository, mPostExecutionThread)
    }

    @Test
    fun bookmarkProjectCompletes() {
        stubBookmarkProject(Completable.complete())
        val mTestObserver = mBookmarkProject.buildUseCaseCompletable(
            BookmarkProject.Params.forProject(ProjectDataFactory.randomUuid())
        ).test()
        mTestObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun bookmarkProjectThrowsException() {
        mBookmarkProject.buildUseCaseCompletable().test()
    }

    private fun stubBookmarkProject(mCompletable: Completable) {
        whenever(mProjectRepository.bookmarkProject(any()))
            .thenReturn(mCompletable)
    }
}