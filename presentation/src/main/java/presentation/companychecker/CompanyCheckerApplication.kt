package presentation.companychecker

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import presentation.companychecker.di.components.ApplicationComponent
import presentation.companychecker.di.components.DaggerApplicationComponent
import javax.inject.Inject


class CompanyCheckerApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    private val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()

        injectMembers()
    }

    private fun injectMembers() {
        appComponent.inject(this)
    }

}
