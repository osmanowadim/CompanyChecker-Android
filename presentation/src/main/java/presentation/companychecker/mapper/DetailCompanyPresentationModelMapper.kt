package presentation.companychecker.mapper

import domain.companychecker.model.*
import presentation.companychecker.model.*
import javax.inject.Inject

/**
 * Mapper class used to :
 * transformDetailCompanyToPresentationModel {@link [DetailCompany]} (in the domain layer)
 * to {@link [DetailCompanyPresentationModel]} in the presentation layer.
 * transformPresentationModelToDetailCompany {@link [DetailCompanyPresentationModel]} (in the presentation layer)
 * to {@link [DetailCompany]} in the domain layer.
 */
class DetailCompanyPresentationModelMapper @Inject constructor() {

    /**
     * Transform a {@link [DetailCompany]} into an {@link [DetailCompanyPresentationModel]}.
     *
     * @param detailCompany {@link [DetailCompany]} Object to be transformed.
     * @return {@link [DetailCompanyPresentationModel]} if valid {@link [DetailCompany]}.
     */
    fun transformDetailCompanyToPresentationModel(detailCompany: DetailCompany) = with(detailCompany) {
        DetailCompanyPresentationModel(
            getArticlesEbanoePresentationModel(this.ebanoe),
            getDouPresentationModel(this.dou)
        )
    }

    /**
     * Transform a {@link [DetailCompanyPresentationModel]} into an {@link [DetailCompany]}.
     *
     * @param detailCompanyPresentationModel Object to be transformed.
     * @return {@link [DetailCompany]} if valid {@link [DetailCompanyPresentationModel]}.
     */
    fun transformPresentationModelToDetailCompany(detailCompanyPresentationModel: DetailCompanyPresentationModel) =
        with(detailCompanyPresentationModel) {
            DetailCompany(
                getArticlesEbanoe(this.ebanoe),
                getDou(this.dou)
            )
        }

    /**
     * Transform a {@link [ArticlesEbanoe]} into an {@link [ArticlesEbanoePresentationModel]}.
     *
     * @param articlesEbanoe Object to be transformed.
     * @return {@link [ArticlesEbanoePresentationModel]} if valid {@link [ArticlesEbanoe]}.
     */
    private fun getArticlesEbanoePresentationModel(articlesEbanoe: ArticlesEbanoe?): ArticlesEbanoePresentationModel? =
        with(articlesEbanoe) {
            ArticlesEbanoePresentationModel(getListArticlesBodyPresentationModel(this?.articles))
        }

    /**
     * Transform a list of {@link [ArticlesBody]} into a list of {@link [ArticlesBodyPresentationModel]}.
     *
     * @param articlesBodyList list of Objects to be transformed.
     * @return list of {@link [ArticlesBodyPresentationModel]} if valid list of {@link [ArticlesBody]}.
     */
    private fun getListArticlesBodyPresentationModel(articlesBodyList: List<ArticlesBody>?): List<ArticlesBodyPresentationModel>? =
        with(articlesBodyList) {
            this?.map {
                ArticlesBodyPresentationModel(
                    it.title,
                    it.url
                )
            }
        }

    /**
     * Transform a {@link [Dou]} into an {@link [DouPresentationModel]}.
     *
     * @param dou Object to be transformed.
     * @return {@link [DouPresentationModel]} if valid {@link [Dou]}.
     */
    private fun getDouPresentationModel(dou: Dou?): DouPresentationModel? =
        with(dou) {
            DouPresentationModel(
                getListRatingBodyPresentationModel(this?.rating),
                getListReviewsBodyPresentationModel(this?.reviews),
                getListVacanciesBodyPresentationModel(this?.vacancies)
            )
        }

    /**
     * Transform a list of {@link [RatingBody]} into a list of {@link [RatingBodyPresentationModel]}.
     *
     * @param ratingBodyList list of Objects to be transformed.
     * @return list of {@link [RatingBodyPresentationModel]} if valid list of {@link [RatingBody]}.
     */
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

    /**
     * Transform a list of {@link [ReviewsBody]} into a list of {@link [ReviewsBodyPresentationModel]}.
     *
     * @param reviewsBodyList list of Objects to be transformed.
     * @return list of {@link [ReviewsBodyPresentationModel]} if valid list of {@link [ReviewsBody]}.
     */
    private fun getListReviewsBodyPresentationModel(reviewsBodyList: List<ReviewsBody>?): List<ReviewsBodyPresentationModel>? =
        with(reviewsBodyList) {
            this?.map {
                ReviewsBodyPresentationModel(
                    getCompanyBodyPresentationModel(it.companyBodyEntity),
                    getReviewsPresentationModel(it.reviewsBodyEntity)
                )
            }
        }

    /**
     * Transform a list of {@link [VacanciesBody]} into a list of {@link [VacanciesBodyPresentationModel]}.
     *
     * @param vacanciesBodyList list of Objects to be transformed.
     * @return list of {@link [VacanciesBodyPresentationModel]} if valid list of {@link [VacanciesBody]}.
     */
    private fun getListVacanciesBodyPresentationModel(vacanciesBodyList: List<VacanciesBody>?): List<VacanciesBodyPresentationModel>? =
        with(vacanciesBodyList) {
            this?.map {
                VacanciesBodyPresentationModel(
                    getCompanyBodyPresentationModel(it.companyBodyEntity),
                    getVacanciesPresentationModel(it.vacanciesBodyEntity)
                )
            }
        }

    /**
     * Transform a {@link [CompanyBody]} into an {@link [CompanyBodyPresentationModel]}.
     *
     * @param companyBody Objects to be transformed.
     * @return {@link [CompanyBodyPresentationModel]} if valid {@link [CompanyBody]}.
     */
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

    /**
     * Transform a list of {@link [Vacancies]} into a list of {@link [VacanciesPresentationModel]}.
     *
     * @param vacanciesList list of Objects to be transformed.
     * @return list of {@link [VacanciesPresentationModel]} if valid list of {@link [Vacancies]}.
     */
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

    /**
     * Transform a list of {@link [Reviews]} into a list of {@link [ReviewsPresentationModel]}.
     *
     * @param reviewsList list of Objects to be transformed.
     * @return list of {@link [ReviewsPresentationModel]} if valid list of {@link [Reviews]}.
     */
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

    /**
     * Transform a {@link [ArticlesEbanoePresentationModel]} into an {@link [ArticlesEbanoe]}.
     *
     * @param articlesEbanoePresentationModel Objects to be transformed.
     * @return {@link [ArticlesEbanoe]} if valid {@link [ArticlesEbanoePresentationModel]}.
     */
    private fun getArticlesEbanoe(articlesEbanoePresentationModel: ArticlesEbanoePresentationModel?): ArticlesEbanoe? =
        with(articlesEbanoePresentationModel) {
            ArticlesEbanoe(getListArticlesBody(this?.articles))
        }

    /**
     * Transform a list of {@link [ArticlesBodyPresentationModel]} into a list of {@link [ArticlesBody]}.
     *
     * @param articlesBodyListPresentationModel list of Objects to be transformed.
     * @return list of {@link [ArticlesBody]} if valid list of {@link [ArticlesBodyPresentationModel]}.
     */
    private fun getListArticlesBody(articlesBodyListPresentationModel: List<ArticlesBodyPresentationModel>?): List<ArticlesBody>? =
        with(articlesBodyListPresentationModel) {
            this?.map {
                ArticlesBody(
                    it.title,
                    it.url
                )
            }
        }

    /**
     * Transform a {@link [DouPresentationModel]} into a {@link [Dou]}.
     *
     * @param dou Objects to be transformed.
     * @return {@link [Dou]} if valid {@link [DouPresentationModel]}.
     */
    private fun getDou(dou: DouPresentationModel?): Dou? =
        with(dou) {
            Dou(
                getListRatingBody(this?.rating),
                getListReviewsBody(this?.reviews),
                getListVacanciesBody(this?.vacancies)
            )
        }

    /**
     * Transform a list of {@link [RatingBodyPresentationModel]} into a list of {@link [RatingBody]}.
     *
     * @param ratingBodyListPresentationModel list of Objects to be transformed.
     * @return list of {@link [RatingBody]} if valid list of {@link [RatingBodyPresentationModel]}.
     */
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

    /**
     * Transform a list of {@link [ReviewsBodyPresentationModel]} into a list of {@link [ReviewsBody]}.
     *
     * @param reviewsBodyListPresentationModel list of Objects to be transformed.
     * @return list of {@link [ReviewsBody]} if valid list of {@link [ReviewsBodyPresentationModel]}.
     */
    private fun getListReviewsBody(reviewsBodyListPresentationModel: List<ReviewsBodyPresentationModel>?): List<ReviewsBody>? =
        with(reviewsBodyListPresentationModel) {
            this?.map {
                ReviewsBody(
                    getCompanyBody(it.companyBody),
                    getReviews(it.reviewsBody)
                )
            }
        }

    /**
     * Transform a list of {@link [VacanciesBodyPresentationModel]} into a list of {@link [VacanciesBody]}.
     *
     * @param vacanciesBodyListPresentationModel list of Objects to be transformed.
     * @return list of {@link [VacanciesBody]} if valid list of {@link [VacanciesBodyPresentationModel]}.
     */
    private fun getListVacanciesBody(vacanciesBodyListPresentationModel: List<VacanciesBodyPresentationModel>?): List<VacanciesBody>? =
        with(vacanciesBodyListPresentationModel) {
            this?.map {
                VacanciesBody(
                    getCompanyBody(it.companyBody),
                    getVacanciesList(it.vacanciesBody)
                )
            }
        }

    /**
     * Transform a {@link [CompanyBodyPresentationModel]} into a {@link [CompanyBody]}.
     *
     * @param companyBodyPresentationModel Objects to be transformed.
     * @return {@link [CompanyBody]} if valid {@link [CompanyBodyPresentationModel]}.
     */
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

    /**
     * Transform a {@link [VacanciesPresentationModel]} into a {@link [Vacancies]}.
     *
     * @param vacanciesPresentationModelList Objects to be transformed.
     * @return {@link [Vacancies]} if valid {@link [VacanciesPresentationModel]}.
     */
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

    /**
     * Transform a {@link [ReviewsPresentationModel]} into a {@link [Reviews]}.
     *
     * @param reviewsPresentationModelList Objects to be transformed.
     * @return {@link [Reviews]} if valid {@link [ReviewsPresentationModel]}.
     */
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
