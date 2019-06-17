package com.mohammad.data.test.factory

import com.mohammad.data.model.ProjectEntity
import com.mohammad.domain.model.Project

object ProjectFactory {

    fun makeProjectEntity(): ProjectEntity {
        return ProjectEntity(
            DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomBoolean()
        )
    }

    fun makeProject():Project{
        return Project(DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomBoolean())
    }
}