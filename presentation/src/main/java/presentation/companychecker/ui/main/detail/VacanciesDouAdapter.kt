package presentation.companychecker.ui.main.detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_detail_vacancy.view.*
import presentation.companychecker.R
import presentation.companychecker.model.VacanciesPresentationModel

class VacanciesDouAdapter(
    private var vacancies: List<VacanciesPresentationModel>,
    private val articleClickListener: (String) -> Unit
) : RecyclerView.Adapter<VacanciesDouAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_detail_vacancy, parent, false))

    override fun getItemCount() = vacancies.size

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(vacancies[position])


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(vacancy: VacanciesPresentationModel): Unit = with(itemView) {
            if (vacancy.title != null && vacancy.url != null) {
                item_detail_vacancy_text.text = vacancy.title
                item_detail_vacancy_link_iv.setOnClickListener {
                    articleClickListener(vacancy.url)
                }
            } else {
                item_detail_vacancy_container.visibility = View.GONE
            }
        }

    }


}