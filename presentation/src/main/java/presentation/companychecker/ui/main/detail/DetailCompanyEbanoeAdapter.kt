package presentation.companychecker.ui.main.detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_detail_company_ebanoe.view.*
import presentation.companychecker.R
import presentation.companychecker.model.ArticlesBodyPresentationModel

class DetailCompanyEbanoeAdapter(
    private var articles: List<ArticlesBodyPresentationModel>,
    private val articleClickListener: (String) -> Unit
) : RecyclerView.Adapter<DetailCompanyEbanoeAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_detail_company_ebanoe, parent, false))

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(articles[position])


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(article: ArticlesBodyPresentationModel): Unit = with(itemView) {
            if (article.title != null && article.url != null) {
                item_detail_company_ebanoe_text.text = article.title
                item_detail_company_ebanoe_link_iv.setOnClickListener {
                    articleClickListener(article.url)
                }
            } else {
                item_detail_company_ebanoe_container.visibility = View.GONE
            }
        }

    }

}
