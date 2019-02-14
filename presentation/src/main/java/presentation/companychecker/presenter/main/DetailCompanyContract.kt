package presentation.companychecker.presenter.main

import currencyconverter.presentation.InternetDependsView
import presentation.companychecker.BasePresenter
import presentation.companychecker.BaseView
import presentation.companychecker.model.DetailCompanyPresentationModel

interface DetailCompanyContract {

    interface View : BaseView<Presenter>, InternetDependsView {

        fun showDetailCompanyInfo(detailCompanyPresentationModel: DetailCompanyPresentationModel)

        fun showLoading()

        fun hideLoading()

        fun showError()

    }

    interface Presenter : BasePresenter {

        fun setCompanyName(companyName: String)

    }

}