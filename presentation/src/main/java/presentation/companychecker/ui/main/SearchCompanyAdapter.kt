package presentation.companychecker.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_company.view.*
import presentation.companychecker.R
import presentation.companychecker.model.CompanyPresentationModel

class SearchCompanyAdapter(
    private var companies: MutableList<CompanyPresentationModel>,
    private val companyClickListener: (Int) -> Unit
) : RecyclerView.Adapter<SearchCompanyAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_company, parent, false))

    override fun getItemCount() = companies.size

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(companies[position], position)

    fun clearItems() {
        val size = companies.size
        companies.clear()
        notifyItemRangeRemoved(0, size)
    }


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(company: CompanyPresentationModel, position: Int): Unit = with(itemView) {
            company.image?.let {
                Glide.with(this)
                    .applyDefaultRequestOptions(
                        RequestOptions()
                            .error(R.drawable.default_image)
                    )
                    .load(it)
                    .into(item_company_iv)
            } ?: Glide.with(item_company_iv)
                .load(R.drawable.default_image)
                .into(item_company_iv)

            company.name?.let {
                item_company_tv.text = it
            } ?: setDefaultText(item_company_tv, resources.getString(R.string.empty_company_name))

            item_company_container.setOnClickListener {
                companyClickListener(position)
            }
        }

        private fun setDefaultText(tv: TextView, text: String) {
            tv.text = text
        }

    }

}
