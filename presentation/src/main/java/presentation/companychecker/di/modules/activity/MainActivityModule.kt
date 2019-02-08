package presentation.companychecker.di.modules.activity

import dagger.Module
import dagger.Provides
import domain.companychecker.scopes.PerActivity
import presentation.companychecker.mapper.CompanyPresentationModelMapper
import presentation.companychecker.presenter.main.MainContract
import presentation.companychecker.presenter.main.MainPresenter
import presentation.companychecker.ui.main.MainActivity

@Module
open class MainActivityModule {

    @PerActivity
    @Provides
    fun provideView(activity: MainActivity) = activity as MainContract.View

    @PerActivity
    @Provides
    fun providePresenter(
        view: MainContract.View,
        mapperCompany: CompanyPresentationModelMapper
    ): MainContract.Presenter {
        return MainPresenter(view, mapperCompany)
    }

}
