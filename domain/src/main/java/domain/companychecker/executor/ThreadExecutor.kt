package domain.companychecker.executor

import java.util.concurrent.Executor

/**
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the
 * {@link [domain.companychecker.interactor.SingleUseCase]} out of the UI thread.
 */
interface ThreadExecutor : Executor
