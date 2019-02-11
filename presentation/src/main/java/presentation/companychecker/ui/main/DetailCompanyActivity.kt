package presentation.companychecker.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import kotlinx.android.synthetic.main.activity_detail_company.*
import presentation.companychecker.R
import presentation.companychecker.extension.hide
import presentation.companychecker.extension.show
import presentation.companychecker.extension.snackbar
import presentation.companychecker.presenter.main.DetailCompanyContract
import presentation.companychecker.ui.main.DetailCompanyActivity.Companion.COMPANY_IMAGE_URL_EXTRA_KEY
import presentation.companychecker.ui.main.DetailCompanyActivity.Companion.COMPANY_NAME_EXTRA_KEY
import presentation.companychecker.ui.main.DetailCompanyActivity.Companion.COMPANY_URL_EXTRA_KEY
import javax.inject.Inject

fun Context.companyDetailActivityIntent(name: String?, image: String?, url: String?) =
    Intent(this, DetailCompanyActivity::class.java).apply {
        putExtra(COMPANY_NAME_EXTRA_KEY, name)
        putExtra(COMPANY_IMAGE_URL_EXTRA_KEY, image)
        putExtra(COMPANY_URL_EXTRA_KEY, url)
    }

class DetailCompanyActivity : AppCompatActivity(), HasActivityInjector, DetailCompanyContract.View {

    override fun activityInjector(): AndroidInjector<Activity> = injector

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var detailCompanyPresenter: DetailCompanyContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_company)

        detailCompanyPresenter.setCompanyName(intent.getStringExtra(COMPANY_NAME_EXTRA_KEY))
        detailCompanyPresenter.start()
        init()
    }

    override fun setPresenter(presenter: DetailCompanyContract.Presenter) {
        detailCompanyPresenter = presenter
    }

    override fun isInternetAvailable(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }

    override fun showNoInternetConnection() {
        snackbar(activity_detail_company_container, R.string.error_internet_connection)
    }

    override fun showLoading() {
        activity_detail_company_progress.show()
    }

    override fun hideLoading() {
        activity_detail_company_progress.hide()
    }

    private fun init() {

    }


    companion object {

        const val COMPANY_NAME_EXTRA_KEY = "COMPANY_NAME_EXTRA_KEY"
        const val COMPANY_IMAGE_URL_EXTRA_KEY = "COMPANY_IMAGE_URL_EXTRA_KEY"
        const val COMPANY_URL_EXTRA_KEY = "COMPANY_URL_EXTRA_KEY"

    }

}
