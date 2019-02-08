package presentation.companychecker.di.modules.activity

import dagger.Module
import dagger.Provides
import domain.companychecker.scopes.PerActivity
import presentation.companychecker.presenter.splash.SplashContract
import presentation.companychecker.presenter.splash.SplashPresenter
import presentation.companychecker.ui.splash.SplashActivity

@Module
open class SplashActivityModule {

    @PerActivity
    @Provides
    fun provideView(activity: SplashActivity) = activity as SplashContract.View

    @PerActivity
    @Provides
    fun providePresenter(
        view: SplashContract.View
    ): SplashContract.Presenter {
        return SplashPresenter(view)
    }

}
