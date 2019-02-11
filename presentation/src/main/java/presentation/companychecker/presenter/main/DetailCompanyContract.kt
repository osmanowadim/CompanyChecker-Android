package presentation.companychecker.presenter.main

import currencyconverter.presentation.InternetDependsView
import presentation.companychecker.BasePresenter
import presentation.companychecker.BaseView

interface DetailCompanyContract {

    interface View : BaseView<Presenter>, InternetDependsView {

        fun showLoading()

        fun hideLoading()

    }

    interface Presenter : BasePresenter {

        fun setCompanyName(companyName: String)

    }

}