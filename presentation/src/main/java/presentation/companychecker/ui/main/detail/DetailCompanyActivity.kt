package presentation.companychecker.ui.main.detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.animation.AnimationUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import kotlinx.android.synthetic.main.activity_detail_company.*
import me.toptas.fancyshowcase.FancyShowCaseView
import me.toptas.fancyshowcase.FocusShape
import me.toptas.fancyshowcase.listener.DismissListener
import presentation.companychecker.R
import presentation.companychecker.extension.animateChangingActivityFade
import presentation.companychecker.extension.hide
import presentation.companychecker.extension.show
import presentation.companychecker.extension.snackbar
import presentation.companychecker.model.DetailCompanyPresentationModel
import presentation.companychecker.presenter.main.DetailCompanyContract
import presentation.companychecker.ui.main.detail.DetailCompanyActivity.Companion.COMPANY_IMAGE_URL_EXTRA_KEY
import presentation.companychecker.ui.main.detail.DetailCompanyActivity.Companion.COMPANY_NAME_EXTRA_KEY
import presentation.companychecker.ui.main.detail.DetailCompanyActivity.Companion.COMPANY_URL_EXTRA_KEY
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
        activity_detail_company_rating_progress.show()
    }

    override fun hideLoading() {
        activity_detail_company_progress.hide()
        activity_detail_company_rating_progress.hide()
    }

    override fun onBackPressed() {
        animateChangingActivityFade()
        finish()
    }

    override fun showDetailCompanyInfo(detailCompanyPresentationModel: DetailCompanyPresentationModel) {
        detailCompanyPresentationModel.dou?.rating?.let {
            if (it.count() > 1) {
                //ToDo: Show dialog choose company
                initViews(detailCompanyPresentationModel)
            } else initViews(detailCompanyPresentationModel)
        } ?: initViews(detailCompanyPresentationModel)
    }

    private fun initViews(detailCompanyPresentationModel: DetailCompanyPresentationModel) {
        if (detailCompanyPresentationModel.dou?.rating?.firstOrNull()?.place == null
            && detailCompanyPresentationModel.dou?.rating?.firstOrNull()?.score == null
        ) changeVisibilityForView(activity_detail_company_rating_cardView, false)
        else changeVisibilityForView(activity_detail_company_rating_cardView, true)

        detailCompanyPresentationModel.dou?.rating?.firstOrNull()
            ?.place?.let { activity_detail_company_rating_place_tv.text = getString(R.string.rating_place, it) }
        detailCompanyPresentationModel.dou?.rating?.firstOrNull()
            ?.score?.let { activity_detail_company_rating_score_tv.text = getString(R.string.rating_score, it) }
        detailCompanyPresentationModel.dou?.reviews?.firstOrNull()?.reviewsBodyEntity?.count()?.let {
            if (it != 0) {
                activity_detail_company_dou_main_count_tv.text = it.toString()
                changeVisibilityForView(activity_detail_company_dou_cardView, true)
            } else {
                changeVisibilityForView(activity_detail_company_dou_cardView, false)
            }
        }
        detailCompanyPresentationModel.ebanoe?.articles?.count()?.let {
            if (it != 0) {
                activity_detail_company_ebanoe_main_count_tv.text = it.toString()
                changeVisibilityForView(activity_detail_company_ebanoe_cardView, true)
            } else {
                changeVisibilityForView(activity_detail_company_ebanoe_cardView, false)
            }
        }
        activity_detail_company_dou_cardView.setOnClickListener {
            if (activity_detail_company_dou_recyclerView.visibility == View.VISIBLE) {
                showChangeArrowAnimation(activity_detail_company_dou_main_arrow_iv, false)
                changeVisibilityForView(activity_detail_company_dou_recyclerView, false)
            } else {
                activity_detail_company_dou_recyclerView.apply {
                    layoutManager = LinearLayoutManager(this.context)
                    detailCompanyPresentationModel.dou?.reviews?.firstOrNull()?.reviewsBodyEntity?.let { reviews ->
                        adapter = DetailCompanyDouAdapter(reviews) { url ->
                            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                        }
                    }
                }
                showChangeArrowAnimation(activity_detail_company_dou_main_arrow_iv, true)
                changeVisibilityForView(activity_detail_company_dou_recyclerView, true)
            }
        }
        activity_detail_company_ebanoe_cardView.setOnClickListener {
            if (activity_detail_company_ebanoe_recyclerView.visibility == View.VISIBLE) {
                showChangeArrowAnimation(activity_detail_company_ebanoe_main_arrow_iv, false)
                changeVisibilityForView(activity_detail_company_ebanoe_recyclerView, false)
            } else {
                activity_detail_company_ebanoe_recyclerView.apply {
                    layoutManager = LinearLayoutManager(this.context)
                    detailCompanyPresentationModel.ebanoe?.articles?.let { articles ->
                        adapter =
                            DetailCompanyEbanoeAdapter(articles) { url ->
                                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                            }
                    }
                }
                showChangeArrowAnimation(activity_detail_company_ebanoe_main_arrow_iv, true)
                changeVisibilityForView(activity_detail_company_ebanoe_recyclerView, true)
            }
        }
        showTutorialsIfNeed()
    }

    private fun showTutorialsIfNeed() {
        if (activity_detail_company_dou_cardView.visibility == View.VISIBLE) showDouTutorial()
        else if (activity_detail_company_ebanoe_cardView.visibility == View.VISIBLE) showEbanoeTutorial()
    }

    private fun showDouTutorial() {
        FancyShowCaseView.Builder(this)
            .focusOn(activity_detail_company_dou_main_arrow_iv)
            .focusShape(FocusShape.CIRCLE)
            .enableTouchOnFocusedView(true)
            .enableAutoTextPosition()
            .title(getString(R.string.dou_tutorial_text))
            .showOnce("dou_reviews")
            .dismissListener(object : DismissListener {
                override fun onDismiss(id: String?) {
                    if (activity_detail_company_ebanoe_cardView.visibility == View.VISIBLE) showEbanoeTutorial()
                }

                override fun onSkipped(id: String?) {
                    if (activity_detail_company_ebanoe_cardView.visibility == View.VISIBLE) showEbanoeTutorial()
                }
            })
            .build()
            .show()
    }

    private fun showEbanoeTutorial() {
        FancyShowCaseView.Builder(this)
            .focusOn(activity_detail_company_ebanoe_main_arrow_iv)
            .focusShape(FocusShape.CIRCLE)
            .enableTouchOnFocusedView(true)
            .enableAutoTextPosition()
            .title(getString(R.string.ebanoe_tutorial_text))
            .showOnce("ebanoe_reviews")
            .build()
            .show()
    }

    private fun init() {
        activity_detail_company_name.text = intent.getStringExtra(COMPANY_NAME_EXTRA_KEY)

        intent.getStringExtra(COMPANY_IMAGE_URL_EXTRA_KEY)?.let {
            Glide.with(this)
                .applyDefaultRequestOptions(
                    RequestOptions()
                        .error(R.drawable.default_image)
                )
                .load(it)
                .into(activity_detail_company_logo)
        } ?: Glide.with(this)
            .load(R.drawable.default_image)
            .into(activity_detail_company_logo)

        activity_detail_company_rating_cardView.setOnClickListener {
            intent.getStringExtra(COMPANY_URL_EXTRA_KEY)?.let { url ->
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            } ?: showUnluckyMessage()
        }
    }

    private fun showUnluckyMessage() {
        snackbar(activity_detail_company_container, R.string.unlucky_message)
    }

    private fun changeVisibilityForView(view: View, isVisible: Boolean) {
        val animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val animationFadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)
        view.visibility =
            if (isVisible) {
                view.startAnimation(animationFadeIn)
                View.VISIBLE
            } else {
                view.startAnimation(animationFadeOut)
                View.GONE
            }
    }

    private fun showChangeArrowAnimation(view: View, isVisible: Boolean) {
        view.animate().apply {
            duration = 200
            if (isVisible) rotation(180f)
            else rotation(0f)
        }
    }


    companion object {

        const val COMPANY_NAME_EXTRA_KEY = "COMPANY_NAME_EXTRA_KEY"
        const val COMPANY_IMAGE_URL_EXTRA_KEY = "COMPANY_IMAGE_URL_EXTRA_KEY"
        const val COMPANY_URL_EXTRA_KEY = "COMPANY_URL_EXTRA_KEY"

    }

}
