package presentation.companychecker.di.modules.binding

import dagger.Module
import dagger.android.ContributesAndroidInjector
import domain.companychecker.scopes.PerActivity
import presentation.companychecker.di.modules.activity.DetailCompanyActivityModule
import presentation.companychecker.di.modules.activity.MainActivityModule
import presentation.companychecker.di.modules.activity.SplashActivityModule
import presentation.companychecker.ui.main.DetailCompanyActivity
import presentation.companychecker.ui.main.MainActivity
import presentation.companychecker.ui.splash.SplashActivity

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [SplashActivityModule::class])
    abstract fun bindSplashActivity(): SplashActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [DetailCompanyActivityModule::class])
    abstract fun bindDetailCompanyActivity(): DetailCompanyActivity

}
