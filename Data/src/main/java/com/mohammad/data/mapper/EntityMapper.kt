package com.mohammad.data.mapper

interface EntityMapper<E, D> {

    fun mapFromEntity(mEntity: E): D

    fun mapTOEntity(mDomain: D): E

}