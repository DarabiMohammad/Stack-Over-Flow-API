package com.mohammad.data.repository

import com.mohammad.data.model.ProjectEntity
import io.reactivex.Observable

interface ProjectsRemote {

    fun getProjects():Observable<List<ProjectEntity>>

}