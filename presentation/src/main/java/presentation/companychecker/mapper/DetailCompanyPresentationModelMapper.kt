package presentation.companychecker.mapper

import domain.companychecker.model.*
import presentation.companychecker.model.*
import javax.inject.Inject

class DetailCompanyPresentationModelMapper @Inject constructor() {

    fun transformDetailCompanyToPresentationModel(detailCompany: DetailCompany) = with(detailCompany) {
        DetailCompanyPresentationModel(
            getArticlesEbanoePresentationModel(this.ebanoe),
            getDouPresentationModel(this.dou)
        )
    }

    fun transformPresentationModelToDetailCompany(detailCompanyPresentationModel: DetailCompanyPresentationModel) =
        with(detailCompanyPresentationModel) {
            DetailCompany(
                getArticlesEbanoe(this.ebanoe),
                getDou(this.dou)
            )
        }

    private fun getArticlesEbanoePresentationModel(articlesEbanoe: ArticlesEbanoe?): ArticlesEbanoePresentationModel? =
        with(articlesEbanoe) {
            ArticlesEbanoePresentationModel(getListArticlesBodyPresentationModel(this?.articles))
        }

    private fun getListArticlesBodyPresentationModel(articlesBodyList: List<ArticlesBody>?): List<ArticlesBodyPresentationModel>? =
        with(articlesBodyList) {
            this?.map {
                ArticlesBodyPresentationModel(
                    it.title,
                    it.url
                )
            }
        }

    private fun getDouPresentationModel(dou: Dou?): DouPresentationModel? =
        with(dou) {
            DouPresentationModel(
                getListRatingBodyPresentationModel(this?.rating),
                getListReviewsBodyPresentationModel(this?.reviews),
                getListVacanciesBodyPresentationModel(this?.vacancies)
            )
        }

    private fun getListRatingBodyPresentationModel(ratingBodyList: List<RatingBody>?): List<RatingBodyPresentationModel>? =
        with(ratingBodyList) {
            this?.map {
                RatingBodyPresentationModel(
                    it.name,
                    it.place,
                    it.url,
                    it.score,
                    it.inCategory,
                    it.profilesCount
                )
            }
        }

    private fun getListReviewsBodyPresentationModel(reviewsBodyList: List<ReviewsBody>?): List<ReviewsBodyPresentationModel>? =
        with(reviewsBodyList) {
            this?.map {
                ReviewsBodyPresentationModel(
                    getCompanyBodyPresentationModel(it.companyBodyEntity),
                    getReviewsPresentationModel(it.reviewsBodyEntity)
                )
            }
        }

    private fun getListVacanciesBodyPresentationModel(vacanciesBodyList: List<VacanciesBody>?): List<VacanciesBodyPresentationModel>? =
        with(vacanciesBodyList) {
            this?.map {
                VacanciesBodyPresentationModel(
                    getCompanyBodyPresentationModel(it.companyBodyEntity),
                    getVacanciesPresentationModel(it.vacanciesBodyEntity)
                )
            }
        }

    private fun getCompanyBodyPresentationModel(companyBody: CompanyBody?): CompanyBodyPresentationModel? =
        with(companyBody) {
            CompanyBodyPresentationModel(
                this?.name,
                this?.image,
                this?.url,
                this?.vacanciesUrl,
                this?.reviewUrl
            )
        }

    private fun getVacanciesPresentationModel(vacanciesList: List<Vacancies>?): List<VacanciesPresentationModel>? =
        with(vacanciesList) {
            this?.map {
                VacanciesPresentationModel(
                    it.id,
                    it.title,
                    it.description,
                    it.url
                )
            }
        }

    private fun getReviewsPresentationModel(reviewsList: List<Reviews>?): List<ReviewsPresentationModel>? =
        with(reviewsList) {
            this?.map {
                ReviewsPresentationModel(
                    it.review,
                    it.url,
                    it.supportCount
                )
            }
        }

    private fun getArticlesEbanoe(articlesEbanoePresentationModel: ArticlesEbanoePresentationModel?): ArticlesEbanoe? =
        with(articlesEbanoePresentationModel) {
            ArticlesEbanoe(getListArticlesBody(this?.articles))
        }

    private fun getListArticlesBody(articlesBodyListPresentationModel: List<ArticlesBodyPresentationModel>?): List<ArticlesBody>? =
        with(articlesBodyListPresentationModel) {
            this?.map {
                ArticlesBody(
                    it.title,
                    it.url
                )
            }
        }

    private fun getDou(dou: DouPresentationModel?): Dou? =
        with(dou) {
            Dou(
                getListRatingBody(this?.rating),
                getListReviewsBody(this?.reviews),
                getListVacanciesBody(this?.vacancies)
            )
        }

    private fun getListRatingBody(ratingBodyListPresentationModel: List<RatingBodyPresentationModel>?): List<RatingBody>? =
        with(ratingBodyListPresentationModel) {
            this?.map {
                RatingBody(
                    it.name,
                    it.place,
                    it.url,
                    it.score,
                    it.inCategory,
                    it.profilesCount
                )
            }
        }

    private fun getListReviewsBody(reviewsBodyListPresentationModel: List<ReviewsBodyPresentationModel>?): List<ReviewsBody>? =
        with(reviewsBodyListPresentationModel) {
            this?.map {
                ReviewsBody(
                    getCompanyBody(it.companyBody),
                    getReviews(it.reviewsBody)
                )
            }
        }

    private fun getListVacanciesBody(vacanciesBodyListPresentationModel: List<VacanciesBodyPresentationModel>?): List<VacanciesBody>? =
        with(vacanciesBodyListPresentationModel) {
            this?.map {
                VacanciesBody(
                    getCompanyBody(it.companyBody),
                    getVacanciesList(it.vacanciesBody)
                )
            }
        }

    private fun getCompanyBody(companyBodyPresentationModel: CompanyBodyPresentationModel?): CompanyBody? =
        with(companyBodyPresentationModel) {
            CompanyBody(
                this?.name,
                this?.image,
                this?.url,
                this?.vacanciesUrl,
                this?.reviewUrl
            )
        }

    private fun getVacanciesList(vacanciesPresentationModelList: List<VacanciesPresentationModel>?): List<Vacancies>? =
        with(vacanciesPresentationModelList) {
            this?.map {
                Vacancies(
                    it.id,
                    it.title,
                    it.description,
                    it.url
                )
            }
        }

    private fun getReviews(reviewsPresentationModelList: List<ReviewsPresentationModel>?): List<Reviews>? =
        with(reviewsPresentationModelList) {
            this?.map {
                Reviews(
                    it.review,
                    it.url,
                    it.supportCount
                )
            }
        }

}
