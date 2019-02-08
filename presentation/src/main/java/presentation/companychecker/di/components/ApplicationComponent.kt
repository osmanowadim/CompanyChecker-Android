package presentation.companychecker.di.components

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import domain.companychecker.scopes.PerApplication
import presentation.companychecker.CompanyCheckerApplication
import presentation.companychecker.di.modules.ApiModule
import presentation.companychecker.di.modules.ApplicationModule
import presentation.companychecker.di.modules.binding.ActivityBindingModule

/**
 * A component whose lifetime is the life of the application.
 */
@PerApplication
@Component(
    modules = [AndroidSupportInjectionModule::class, ApplicationModule::class,
        ApiModule::class, ActivityBindingModule::class]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: CompanyCheckerApplication)

}
