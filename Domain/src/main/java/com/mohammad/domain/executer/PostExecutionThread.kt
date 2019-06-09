package com.mohammad.domain.executer

import io.reactivex.Scheduler

interface PostExecutionThread {
    val mScheduler: Scheduler
}