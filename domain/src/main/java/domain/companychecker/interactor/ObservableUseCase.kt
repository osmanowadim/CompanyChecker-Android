package domain.companychecker.interactor

import domain.companychecker.executor.PostExecutionThread
import domain.companychecker.executor.ThreadExecutor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * By convention each UseCase implementation will return the result using a {@link [io.reactivex.observers.DisposableObserver]}
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
abstract class ObservableUseCase<T, Params> constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    private val disposables = CompositeDisposable()

    /**
     * Builds an [Observable] which will be used when executing the current [ObservableUseCase].
     */
    internal abstract fun buildUseCase(params: Params): Observable<T>

    /**
     * Executes the current use case.
     *
     * @param observer {@link [io.reactivex.observers.DisposableObserver]} which will be listening to the observable build
     * by {@link #buildUseCaseObservable(Params)} ()} method.
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    fun execute(observer: DisposableObserver<T>, params: Params) {
        val observable = buildUseCase(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.getScheduler())
        addDisposable(observable.subscribeWith(observer))
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

}
