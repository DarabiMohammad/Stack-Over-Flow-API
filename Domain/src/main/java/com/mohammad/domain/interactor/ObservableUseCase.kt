package com.mohammad.domain.interactor

import com.mohammad.domain.executer.PostExecutionThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class ObservableUseCase<T, in Params> constructor(
    private val mPostExecutionThread: PostExecutionThread
) {

    private val mDisposables = CompositeDisposable()

    protected abstract fun buildUseCaseObservable(mParams: Params? = null): Observable<T>

    open fun execute(mObserver: DisposableObserver<T>, mParams: Params? = null) {
        val mObservable = this.buildUseCaseObservable(mParams)
            .subscribeOn(Schedulers.io())
            .observeOn(mPostExecutionThread.mScheduler)
        addDisposable(mObservable.subscribeWith(mObserver))
    }

    private fun dispose() {
        mDisposables.dispose()
    }

    private fun addDisposable(mDisposable: Disposable) {
        mDisposables.add(mDisposable)
    }
}