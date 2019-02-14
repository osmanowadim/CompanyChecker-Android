package presentation.companychecker.presenter.splash

import javax.inject.Inject

/**
 * Presenter class used to control {@link [SplashContract.View]}
 * Created in implementation of {@link [SplashContract.View]}
 */
class SplashPresenter @Inject constructor(
    private val view: SplashContract.View
) : SplashContract.Presenter {

    /**
     * Called in onCreate lifecycle of Activity in {@link [SplashContract.View]}
     *
     * calls the method to navigate into the main screen
     */
    override fun start() {
        view.navigateToMain()
    }

    /**
     * Called in onDestroy lifecycle of Activity in {@link [SplashContract.View]}
     */
    override fun stop() {

    }

}
