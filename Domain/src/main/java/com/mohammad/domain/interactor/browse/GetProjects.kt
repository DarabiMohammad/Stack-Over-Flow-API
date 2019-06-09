package com.mohammad.domain.interactor.browse

import com.mohammad.domain.executer.PostExecutionThread
import com.mohammad.domain.interactor.ObservableUseCase
import com.mohammad.domain.model.Project
import com.mohammad.domain.repository.ProjectsRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetProjects @Inject constructor(
    private val mProjectsRepository: ProjectsRepository,
    mPostExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Project>, Nothing>(mPostExecutionThread) {

    public override fun buildUseCaseObservable(mParams: Nothing?): Observable<List<Project>> {
        return mProjectsRepository.getProjects()
    }
}