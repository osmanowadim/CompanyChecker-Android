package presentation.companychecker.presenter.main

import currencyconverter.presentation.InternetDependsView
import presentation.companychecker.BasePresenter
import presentation.companychecker.BaseView
import presentation.companychecker.model.CompanyPresentationModel

interface MainContract {

    interface View : BaseView<Presenter>, InternetDependsView {

        fun showFoundCompanies(companies: List<CompanyPresentationModel>)

        fun showFoundError()

        fun showLoading()

        fun hideLoading()

    }

    interface Presenter : BasePresenter {

        fun searchCompanies(searchText: String)

    }

}
