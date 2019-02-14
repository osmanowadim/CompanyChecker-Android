package presentation.companychecker.ui.splash

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import presentation.companychecker.R
import presentation.companychecker.extension.animateChangingActivityFade
import presentation.companychecker.presenter.splash.SplashContract
import presentation.companychecker.ui.main.mainActivityIntent
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), HasActivityInjector, SplashContract.View {

    override fun activityInjector(): AndroidInjector<Activity> = injector

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var splashPresenter: SplashContract.Presenter

    /**
     * Callback from lifecycle in {@link [Activity]}
     *
     * @param savedInstanceState Bundle of application
     *
     * call start() in {@link [SplashContract.Presenter]}
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splashPresenter.start()
    }

    override fun setPresenter(presenter: SplashContract.Presenter) {
        splashPresenter = presenter
    }

    /**
     * Start {@link [presentation.companychecker.ui.main.MainActivity]}
     * with handle of 3000 milliseconds
     */
    override fun navigateToMain() {
        val handler = Handler()
        handler.postDelayed({
            startActivity(mainActivityIntent())
            animateChangingActivityFade()
            finish()
        }, 3000)

    }

    /**
     * Callback from lifecycle in {@link [Activity]}
     *
     * call stop() in {@link [SplashContract.Presenter]}
     */
    override fun onDestroy() {
        super.onDestroy()
        splashPresenter.stop()
    }

}
