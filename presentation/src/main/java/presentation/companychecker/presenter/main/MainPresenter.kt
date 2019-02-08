package presentation.companychecker.presenter.main

import presentation.companychecker.mapper.CompanyPresentationModelMapper
import javax.inject.Inject


class MainPresenter @Inject constructor(
    private val view: MainContract.View,
    private val mapperCompany: CompanyPresentationModelMapper
) : MainContract.Presenter {

    override fun start() {

    }

    override fun stop() {

    }

}
