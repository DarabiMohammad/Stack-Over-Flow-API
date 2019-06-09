package com.mohammad.domian.test

import com.mohammad.domain.model.Project
import java.util.*

object ProjectDataFactory {

    fun randomUuid(): String {
        return UUID.randomUUID().toString()
    }

    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }

    fun makeProject(): Project {
        return Project(
            randomUuid(), randomUuid(), randomUuid(), randomUuid(), randomUuid(),
            randomUuid(), randomUuid(), randomBoolean()
        )
    }

    fun makeProjectList(mCount: Int):List<Project>{
        val mProjects = mutableListOf<Project>()
        repeat(mCount){
            mProjects.add(makeProject())
        }
        return mProjects
    }
}