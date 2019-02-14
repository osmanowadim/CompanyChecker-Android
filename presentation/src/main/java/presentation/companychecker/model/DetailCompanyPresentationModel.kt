package presentation.companychecker.model

data class DetailCompanyPresentationModel(
    val ebanoe: ArticlesEbanoePresentationModel?,
    val dou: DouPresentationModel?
)

data class ArticlesEbanoePresentationModel(
    val articles: List<ArticlesBodyPresentationModel>?
)

data class ArticlesBodyPresentationModel(
    val title: String?,
    val url: String?
)

data class DouPresentationModel(
    val rating: List<RatingBodyPresentationModel>?,
    val reviews: List<ReviewsBodyPresentationModel>?,
    val vacancies: List<VacanciesBodyPresentationModel>?
)

data class RatingBodyPresentationModel(
    val name: String?,
    val place: Int?,
    val url: String?,
    val score: String?,
    val inCategory: Boolean?,
    val profilesCount: String?
)

data class ReviewsBodyPresentationModel(
    val companyBody: CompanyBodyPresentationModel?,
    val reviewsBody: List<ReviewsPresentationModel>?
)

data class VacanciesBodyPresentationModel(
    val companyBody: CompanyBodyPresentationModel?,
    val vacanciesBody: List<VacanciesPresentationModel>?
)

data class CompanyBodyPresentationModel(
    val name: String?,
    val image: String?,
    val url: String?,
    val vacanciesUrl: String?,
    val reviewUrl: String?
)

data class ReviewsPresentationModel(
    val review: String?,
    val url: String?,
    val supportCount: Int?
)

data class VacanciesPresentationModel(
    val id: String?,
    val title: String?,
    val description: String?,
    val url: String?
)
