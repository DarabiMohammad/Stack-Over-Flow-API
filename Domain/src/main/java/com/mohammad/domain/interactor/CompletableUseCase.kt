package com.mohammad.domain.interactor

import com.mohammad.domain.executer.PostExecutionThread
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

abstract class CompletableUseCase<in Params> constructor(
    private val mPostExecutionThread: PostExecutionThread
) {

    private val mDisposables = CompositeDisposable()

    protected abstract fun buildUseCaseCompletable(mParams: Params? = null): Completable

    open fun execute(mObserver: DisposableCompletableObserver, mParams: Params? = null) {
        val mCompletable = this.buildUseCaseCompletable(mParams)
            .subscribeOn(Schedulers.io())
            .observeOn(mPostExecutionThread.mScheduler)
        addDisposable(mCompletable.subscribeWith(mObserver))
    }

    private fun dispose() {
        mDisposables.dispose()
    }

    private fun addDisposable(mDisposable: Disposable) {
        mDisposables.add(mDisposable)
    }
}