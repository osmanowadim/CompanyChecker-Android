package presentation.companychecker.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import kotlinx.android.synthetic.main.activity_main.*
import presentation.companychecker.R
import presentation.companychecker.extension.animateChangingActivityFade
import presentation.companychecker.extension.hide
import presentation.companychecker.extension.show
import presentation.companychecker.extension.snackbar
import presentation.companychecker.model.CompanyPresentationModel
import presentation.companychecker.presenter.main.MainContract
import presentation.companychecker.ui.main.detail.companyDetailActivityIntent
import javax.inject.Inject


fun Context.mainActivityIntent() = Intent(this, MainActivity::class.java)

class MainActivity : AppCompatActivity(), HasActivityInjector, MainContract.View {

    override fun activityInjector(): AndroidInjector<Activity> = injector

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var mainPresenter: MainContract.Presenter

    private val recognitionCode = 1994

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainPresenter.start()
        init()
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        mainPresenter = presenter
    }

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.stop()
    }

    override fun isInternetAvailable(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }

    override fun showNoInternetConnection() {
        snackbar(activity_main_container, R.string.error_internet_connection)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == recognitionCode && resultCode == Activity.RESULT_OK) {
            val matches = data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            if (!matches.isEmpty() && matches.size > 0) activity_main_query_input.setText(matches[0])
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun showFoundCompanies(companies: MutableList<CompanyPresentationModel>) {
        activity_main_companies_recycler.adapter = SearchCompanyAdapter(companies) { position ->
            showDetailInfo(companies[position])
        }
        activity_main_companies_recycler.visibility = View.VISIBLE
    }

    override fun showFoundError() {
        snackbar(activity_main_container, R.string.error_found_companies)
    }

    override fun showLoading() {
        activity_main_progress.show()
    }

    override fun hideLoading() {
        activity_main_progress.hide()
    }

    private fun showDetailInfo(company: CompanyPresentationModel) {
        if (isInternetAvailable()) {
            startActivity(companyDetailActivityIntent(company.name, company.image, company.url))
            animateChangingActivityFade()
        } else {
            showNoInternetConnection()
        }
    }

    private fun init() {
        activity_main_search_voice.setOnClickListener {
            startVoiceRecognitionActivity()
        }
        activity_main_search_clear.setOnClickListener {
            activity_main_query_input.text?.let { editable ->
                if (editable.isNotEmpty()) editable.clear()
                else (activity_main_companies_recycler.adapter as SearchCompanyAdapter).clearItems()
            }
        }
        activity_main_query_input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mainPresenter.searchCompanies(activity_main_query_input.text.toString())
            }
        })
        activity_main_companies_recycler.apply {
            layoutManager = LinearLayoutManager(this.context)
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun startVoiceRecognitionActivity() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.voice_search_query))
        }
        startActivityForResult(intent, recognitionCode)
    }

}
