package com.mohammad.domain.interactor.bookmark

import com.mohammad.domain.executer.PostExecutionThread
import com.mohammad.domain.interactor.CompletableUseCase
import com.mohammad.domain.repository.ProjectsRepository
import io.reactivex.Completable
import javax.inject.Inject

class BookmarkProject @Inject constructor(
    private val mProjectsRepository: ProjectsRepository,
    mPostExecutionThread: PostExecutionThread
) : CompletableUseCase<BookmarkProject.Params>(mPostExecutionThread) {

    public override fun buildUseCaseCompletable(mParams: Params?): Completable {
        if (mParams == null) throw IllegalArgumentException("Params Cant Be Null")
        return mProjectsRepository.bookmarkProject(mParams.mProjectId)
    }

    data class Params constructor(val mProjectId: String) {
        companion object {
            fun forProject(mProjectId: String): Params {
                return Params(mProjectId)
            }
        }
    }
}