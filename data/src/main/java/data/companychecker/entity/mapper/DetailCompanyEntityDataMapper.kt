package data.companychecker.entity.mapper

import data.companychecker.entity.*
import domain.companychecker.interactor.Params
import domain.companychecker.model.*
import domain.companychecker.scopes.PerApplication
import javax.inject.Inject

/**
 * Mapper class used to :
 * transformFromEntity {@link [data.companychecker.entity.DetailCompanyEntity]} (in the data layer)
 * to {@link [domain.companychecker.model.DetailCompany]} in the domain layer.
 * transformToEntity {@link [domain.companychecker.model.DetailCompany]} (in the domain layer)
 * to {@link [data.companychecker.entity.DetailCompanyEntity]} in the data layer.
 */
@PerApplication
class DetailCompanyEntityDataMapper @Inject constructor() {

    /**
     * Transform a {@link [data.companychecker.entity.DetailCompanyEntity]} into an {@link [domain.companychecker.model.DetailCompany]}.
     *
     * @param detailCompanyEntity Object to be transformed.
     * @return {@link [domain.companychecker.model.DetailCompany]} if valid {@link [data.companychecker.entity.DetailCompanyEntity]}.
     */
    fun transformFromEntity(detailCompanyEntity: DetailCompanyEntity) = with(detailCompanyEntity) {
        DetailCompany(
            getArticlesEbanoe(this.ebanoe),
            getDou(this.dou)
        )
    }

    /**
     * Transform a {@link [domain.companychecker.model.DetailCompany]} into an {@link [data.companychecker.entity.DetailCompanyEntity]}.
     *
     * @param params Object to be transformed.
     * @return {@link [data.companychecker.entity.DetailCompanyEntity]} if valid {@link [domain.companychecker.interactor.Params]}.
     */
    fun transformToEntity(params: Params?) = with(params) {
        DetailCompanyEntity(
            getArticlesEbanoeEntity((this as DetailCompany).ebanoe),
            getDouEntity(this.dou)
        )
    }

    private fun getArticlesEbanoeEntity(articlesEbanoe: ArticlesEbanoe?): ArticlesEbanoeEntity? =
        with(articlesEbanoe) {
            ArticlesEbanoeEntity(getListArticlesBodyEntity(this?.articles))
        }

    private fun getListArticlesBodyEntity(articlesBodyList: List<ArticlesBody>?): List<ArticlesBodyEntity>? =
        with(articlesBodyList) {
            this?.map {
                ArticlesBodyEntity(
                    it.title,
                    it.url
                )
            }
        }

    private fun getDouEntity(dou: Dou?): DouEntity? =
        with(dou) {
            DouEntity(
                getListRatingBodyEntity(this?.rating),
                getListReviewsBodyEntity(this?.reviews),
                getListVacanciesBodyEntity(this?.vacancies)
            )
        }

    private fun getListRatingBodyEntity(ratingBodyList: List<RatingBody>?): List<RatingBodyEntity>? =
        with(ratingBodyList) {
            this?.map {
                RatingBodyEntity(
                    it.name,
                    it.place,
                    it.url,
                    it.score,
                    it.inCategory,
                    it.profilesCount
                )
            }
        }

    private fun getListVacanciesBodyEntity(vacanciesBodyList: List<VacanciesBody>?): List<VacanciesBodyEntity>? =
        with(vacanciesBodyList) {
            this?.map {
                VacanciesBodyEntity(
                    getCompanyBodyEntity(it.companyBodyEntity),
                    getVacanciesEntity(it.vacanciesBodyEntity)
                )
            }
        }

    private fun getListReviewsBodyEntity(reviewsBodyList: List<ReviewsBody>?): List<ReviewsBodyEntity>? =
        with(reviewsBodyList) {
            this?.map {
                ReviewsBodyEntity(
                    getCompanyBodyEntity(it.companyBodyEntity),
                    getReviewsEntity(it.reviewsBodyEntity)
                )
            }
        }

    private fun getCompanyBodyEntity(companyBody: CompanyBody?): CompanyBodyEntity? =
        with(companyBody) {
            CompanyBodyEntity(
                this?.name,
                this?.image,
                this?.url,
                this?.vacanciesUrl,
                this?.reviewUrl
            )
        }

    private fun getVacanciesEntity(vacanciesBodyList: List<Vacancies>?): List<VacanciesEntity>? =
        with(vacanciesBodyList) {
            this?.map {
                VacanciesEntity(
                    it.id,
                    it.title,
                    it.description,
                    it.url
                )
            }
        }

    private fun getReviewsEntity(reviewsList: List<Reviews>?): List<ReviewsEntity>? =
        with(reviewsList) {
            this?.map {
                ReviewsEntity(
                    it.review,
                    it.url,
                    it.supportCount
                )
            }
        }

    private fun getArticlesEbanoe(articlesEbanoeEntity: ArticlesEbanoeEntity?): ArticlesEbanoe? =
        with(articlesEbanoeEntity) {
            ArticlesEbanoe(getListArticlesBody(this?.articles))
        }

    private fun getListArticlesBody(articlesBodyListEntity: List<ArticlesBodyEntity>?): List<ArticlesBody>? =
        with(articlesBodyListEntity) {
            this?.map {
                ArticlesBody(
                    it.title,
                    it.url
                )
            }
        }

    private fun getDou(douEntity: DouEntity?): Dou? =
        with(douEntity) {
            Dou(
                getListRatingBody(this?.rating),
                getListReviewsBody(this?.reviews),
                getListVacanciesBody(this?.vacancies)
            )
        }

    private fun getListRatingBody(ratingBodyListEntity: List<RatingBodyEntity>?): List<RatingBody>? =
        with(ratingBodyListEntity) {
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

    private fun getListReviewsBody(reviewsBodyListEntity: List<ReviewsBodyEntity>?): List<ReviewsBody>? =
        with(reviewsBodyListEntity) {
            this?.map {
                ReviewsBody(
                    getCompanyBody(it.companyBodyEntity),
                    getReviews(it.reviewsBodyEntity)
                )
            }
        }

    private fun getListVacanciesBody(vacanciesBodyListEntity: List<VacanciesBodyEntity>?): List<VacanciesBody>? =
        with(vacanciesBodyListEntity) {
            this?.map {
                VacanciesBody(
                    getCompanyBody(it.companyBodyEntity),
                    getVacanciesList(it.vacanciesBodyEntity)
                )
            }
        }

    private fun getCompanyBody(companyBodyEntity: CompanyBodyEntity?): CompanyBody? =
        with(companyBodyEntity) {
            CompanyBody(
                this?.name,
                this?.image,
                this?.url,
                this?.vacanciesUrl,
                this?.reviewUrl
            )
        }

    private fun getVacanciesList(vacanciesyEntityList: List<VacanciesEntity>?): List<Vacancies>? =
        with(vacanciesyEntityList) {
            this?.map {
                Vacancies(
                    it.id,
                    it.title,
                    it.description,
                    it.url
                )
            }
        }

    private fun getReviews(reviewsEntityList: List<ReviewsEntity>?): List<Reviews>? =
        with(reviewsEntityList) {
            this?.map {
                Reviews(
                    it.review,
                    it.url,
                    it.supportCount
                )
            }
        }

}
