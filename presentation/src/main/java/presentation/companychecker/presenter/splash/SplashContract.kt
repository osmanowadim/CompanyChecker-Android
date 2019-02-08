package presentation.companychecker.presenter.splash

import presentation.companychecker.BasePresenter
import presentation.companychecker.BaseView

interface SplashContract {

    interface View : BaseView<Presenter> {

        fun navigateToMain()

    }

    interface Presenter : BasePresenter {

    }

}
