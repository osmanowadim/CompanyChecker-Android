package presentation.companychecker.presenter.main

import domain.companychecker.interactor.SingleUseCase
import domain.companychecker.model.Company
import io.reactivex.observers.DisposableSingleObserver
import presentation.companychecker.mapper.CompanyPresentationModelMapper
import javax.inject.Inject


class MainPresenter @Inject constructor(
    private val view: MainContract.View,
    private val mapperCompany: CompanyPresentationModelMapper,
    private val searchUseCase: SingleUseCase<List<Company>, String?>
) : MainContract.Presenter {

    override fun start() {

    }

    override fun stop() {
        searchUseCase.dispose()
    }

    override fun searchCompanies(searchText: String) {
        if (searchText.length >= 3) {
            view.showLoading()
            searchUseCase.execute(object : DisposableSingleObserver<List<Company>>() {

                override fun onSuccess(t: List<Company>) {
                    view.apply {
                        hideLoading()
                        showFoundCompanies(t.map(mapperCompany::transformRatioToPresentationModel))
                    }
                }

                override fun onError(e: Throwable) {
                    view.apply {
                        hideLoading()
                        showFoundError()
                    }
                }
            }, searchText)
        }
    }

}
