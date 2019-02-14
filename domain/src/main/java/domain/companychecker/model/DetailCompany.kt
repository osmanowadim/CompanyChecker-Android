package domain.companychecker.model

import domain.companychecker.interactor.Params

data class DetailCompany(
    val ebanoe: ArticlesEbanoe?,
    val dou: Dou?
) : Params()

data class ArticlesEbanoe(
    val articles: List<ArticlesBody>?
)

data class ArticlesBody(
    val title: String?,
    val url: String?
)

data class Dou(
    val rating: List<RatingBody>?,
    val reviews: List<ReviewsBody>?,
    val vacancies: List<VacanciesBody>?
)

data class RatingBody(
    val name: String?,
    val place: Int?,
    val url: String?,
    val score: String?,
    val inCategory: Boolean?,
    val profilesCount: String?
)

data class ReviewsBody(
    val companyBodyEntity: CompanyBody?,
    val reviewsBodyEntity: List<Reviews>?
)

data class VacanciesBody(
    val companyBodyEntity: CompanyBody?,
    val vacanciesBodyEntity: List<Vacancies>?
)

data class CompanyBody(
    val name: String?,
    val image: String?,
    val url: String?,
    val vacanciesUrl: String?,
    val reviewUrl: String?
)

data class Reviews(
    val review: String?,
    val url: String?,
    val supportCount: Int?
)

data class Vacancies(
    val id: String?,
    val title: String?,
    val description: String?,
    val url: String?
)
