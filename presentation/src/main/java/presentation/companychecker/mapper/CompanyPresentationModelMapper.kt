package presentation.companychecker.mapper

import domain.companychecker.model.Company
import presentation.companychecker.model.CompanyPresentationModel
import javax.inject.Inject

/**
 * Mapper class used to :
 * transformCompanyToPresentationModel {@link [Company]} (in the domain layer)
 * to {@link [CompanyPresentationModel]} in the presentation layer.
 * transformPresentationModelToCompany {@link [CompanyPresentationModel]} (in the presentation layer)
 * to {@link [Company]} in the domain layer.
 */
class CompanyPresentationModelMapper @Inject constructor() {

    /**
     * Transform a {@link [Company]} into an {@link [CompanyPresentationModel]}.
     *
     * @param company {@link [Company]} Object to be transformed.
     * @return {@link [CompanyPresentationModel]} if valid {@link [Company]}.
     */
    fun transformCompanyToPresentationModel(company: Company) = with(company) {
        CompanyPresentationModel(this.name, this.image, this.url)
    }

    /**
     * Transform a {@link [CompanyPresentationModel]} into an {@link [Company]}.
     *
     * @param companyPresentationModel Object to be transformed.
     * @return {@link [Company]} if valid {@link [CompanyPresentationModel]}.
     */
    fun transformPresentationModelToCompany(companyPresentationModel: CompanyPresentationModel) =
        with(companyPresentationModel) {
            Company(this.name, this.image, this.url)
        }

}
