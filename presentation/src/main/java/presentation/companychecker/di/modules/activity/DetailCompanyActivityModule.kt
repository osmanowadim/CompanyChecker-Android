package presentation.companychecker.di.modules.activity

import dagger.Module
import dagger.Provides
import domain.companychecker.interactor.company.GetDetailInfoAboutCompanyUseCase
import domain.companychecker.scopes.PerActivity
import presentation.companychecker.presenter.main.DetailCompanyContract
import presentation.companychecker.presenter.main.DetailCompanyPresenter
import presentation.companychecker.ui.main.DetailCompanyActivity

@Module
open class DetailCompanyActivityModule {

    @PerActivity
    @Provides
    fun provideView(activity: DetailCompanyActivity) = activity as DetailCompanyContract.View

    @PerActivity
    @Provides
    fun providePresenter(
        view: DetailCompanyContract.View,
        getDetailInfoUseCase: GetDetailInfoAboutCompanyUseCase
    ): DetailCompanyContract.Presenter {
        return DetailCompanyPresenter(view, getDetailInfoUseCase)
    }

}
