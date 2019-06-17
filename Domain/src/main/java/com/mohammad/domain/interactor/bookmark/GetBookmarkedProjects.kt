package com.mohammad.domain.interactor.bookmark

import com.mohammad.domain.executer.PostExecutionThread
import com.mohammad.domain.interactor.ObservableUseCase
import com.mohammad.domain.model.Project
import com.mohammad.domain.repository.ProjectsRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetBookmarkedProjects @Inject constructor(
    private val mProjectsRepository: ProjectsRepository,
    mPostExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Project>, Nothing>(mPostExecutionThread) {
    public override fun buildUseCaseObservable(mParams: Nothing?): Observable<List<Project>> {
        return mProjectsRepository.getBookmarkedProjects()
    }
}