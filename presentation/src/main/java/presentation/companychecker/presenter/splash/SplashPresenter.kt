package presentation.companychecker.presenter.splash

import javax.inject.Inject

class SplashPresenter @Inject constructor(
    private val view: SplashContract.View
) : SplashContract.Presenter {


    override fun start() {
        view.navigateToMain()
    }

    override fun stop() {

    }

}
