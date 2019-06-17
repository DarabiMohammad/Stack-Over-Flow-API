package com.mohammad.data.mapper

import com.mohammad.data.model.ProjectEntity
import com.mohammad.domain.model.Project
import javax.inject.Inject

class ProjectMapper @Inject constructor() : EntityMapper<ProjectEntity, Project> {

    override fun mapFromEntity(mEntity: ProjectEntity): Project {
        return Project(mEntity.mId,mEntity.mName,mEntity.mFullName,
            mEntity.mStarCount,mEntity.mDateCreated,
            mEntity.mOwnerName,mEntity.mOwnerAvatar,mEntity.mIsBookmarked)
    }

    override fun mapTOEntity(mDomain: Project): ProjectEntity {
        return ProjectEntity(mDomain.mId,mDomain.mName,mDomain.mFullName,
            mDomain.mStarCount,mDomain.mDateCreated,
            mDomain.mOwnerName,mDomain.mOwnerAvatar,mDomain.mIsBookmarked)
    }
}