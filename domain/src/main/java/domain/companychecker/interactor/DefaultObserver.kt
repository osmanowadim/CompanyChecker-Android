package domain.companychecker.interactor

import io.reactivex.observers.DisposableObserver

/**
 * Default {@link [io.reactivex.observers.DisposableObserver]} base class to be used whenever you want default error handling.
 */
class DefaultObserver<T> : DisposableObserver<T>() {

    override fun onComplete() {
        // do nothing
    }

    override fun onNext(t: T) {
        // do nothing
    }

    override fun onError(e: Throwable) {
        // do nothing
    }

}
