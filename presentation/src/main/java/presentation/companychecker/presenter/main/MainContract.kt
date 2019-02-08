package presentation.companychecker.presenter.main

import currencyconverter.presentation.InternetDependsView
import presentation.companychecker.BasePresenter
import presentation.companychecker.BaseView

interface MainContract {

    interface View : BaseView<Presenter>, InternetDependsView {

    }

    interface Presenter : BasePresenter {

    }

}
