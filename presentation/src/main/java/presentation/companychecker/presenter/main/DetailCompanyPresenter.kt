package presentation.companychecker.presenter.main

import domain.companychecker.interactor.SingleUseCase
import domain.companychecker.model.DetailCompany
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject


class DetailCompanyPresenter @Inject constructor(
    private val view: DetailCompanyContract.View,
    private val getDetailInfoUseCase: SingleUseCase<DetailCompany, String?>
) : DetailCompanyContract.Presenter {

    private var companyName: String? = null

    override fun setCompanyName(companyName: String) {
        this.companyName = companyName
    }

    override fun start() {
        getDetailInfoAboutCompany()
    }

    override fun stop() {
        getDetailInfoUseCase.dispose()
    }

    private fun getDetailInfoAboutCompany() {
        if (view.isInternetAvailable()) {
            view.showLoading()
            getDetailInfoUseCase.execute(object : DisposableSingleObserver<DetailCompany>() {

                override fun onSuccess(t: DetailCompany) {
                    view.hideLoading()
                }

                override fun onError(e: Throwable) {
                    view.hideLoading()
                }
            }, companyName)
        } else {
            view.showNoInternetConnection()
        }
    }

}
