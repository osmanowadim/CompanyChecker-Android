package data.companychecker.entity

import com.google.gson.annotations.SerializedName


data class DetailCompanyEntity(
    @SerializedName("ebanoe")
    val ebanoe: ArticlesEbanoeEntity?,
    @SerializedName("dou")
    val dou: DouEntity?
)

data class ArticlesEbanoeEntity(
    @SerializedName("articles")
    val articles: List<ArticlesBodyEntity>?
)

data class ArticlesBodyEntity(
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?
)

data class DouEntity(
    @SerializedName("rating")
    val rating: List<RatingBodyEntity>?,
    @SerializedName("reviews")
    val reviews: List<ReviewsBodyEntity>?,
    @SerializedName("vacancies")
    val vacancies: List<VacanciesBodyEntity>?
)

data class RatingBodyEntity(
    @SerializedName("name")
    val name: String?,
    @SerializedName("place")
    val place: Int?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("score")
    val score: String?,
    @SerializedName("inCategory")
    val inCategory: Boolean?,
    @SerializedName("profilesCount")
    val profilesCount: String?
)

data class ReviewsBodyEntity(
    @SerializedName("company")
    val companyBodyEntity: CompanyBodyEntity?,
    @SerializedName("reviews")
    val reviewsBodyEntity: List<ReviewsEntity>?
)

data class VacanciesBodyEntity(
    @SerializedName("company")
    val companyBodyEntity: CompanyBodyEntity?,
    @SerializedName("vacancies")
    val vacanciesBodyEntity: List<VacanciesEntity>?
)

data class CompanyBodyEntity(
    @SerializedName("name")
    val name: String?,
    @SerializedName("img")
    val image: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("vacanciesUrl")
    val vacanciesUrl: String?,
    @SerializedName("reviewUrl")
    val reviewUrl: String?
)

data class ReviewsEntity(
    @SerializedName("review")
    val review: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("supportCount")
    val supportCount: Int?
)

data class VacanciesEntity(
    @SerializedName("id")
    val id: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("url")
    val url: String?
)
