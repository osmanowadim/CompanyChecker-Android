package presentation.companychecker.ui.main.detail

import android.os.Build
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_detail_company_dou.view.*
import presentation.companychecker.R
import presentation.companychecker.model.ReviewsPresentationModel

class DetailCompanyDouAdapter(
    private var reviews: List<ReviewsPresentationModel>,
    private val reviewsClickListener: (String) -> Unit
) : RecyclerView.Adapter<DetailCompanyDouAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_detail_company_dou, parent, false))

    override fun getItemCount() = reviews.size

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(reviews[position])


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(review: ReviewsPresentationModel): Unit = with(itemView) {
            if (review.review != null && review.supportCount != null && review.url != null) {
                if (review.supportCount != 0) item_detail_company_dou_rating.text = review.supportCount.toString()
                else item_detail_company_dou_rating.visibility = View.GONE

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    item_detail_company_dou_text.text = (
                            Html.fromHtml(
                                Html.fromHtml(review.review, Html.FROM_HTML_MODE_LEGACY).toString(),
                                Html.FROM_HTML_MODE_LEGACY
                            ))
                } else {
                    item_detail_company_dou_text.text = (Html.fromHtml(Html.fromHtml(review.review).toString()))
                }
                item_detail_company_dou_link_iv.setOnClickListener {
                    reviewsClickListener(review.url)
                }
            } else {
                item_detail_company_dou_container.visibility = View.GONE
            }
        }

    }

}
