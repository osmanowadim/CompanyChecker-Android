package presentation.companychecker.mapper

import domain.companychecker.model.Company
import presentation.companychecker.model.CompanyPresentationModel
import javax.inject.Inject

class CompanyPresentationModelMapper @Inject constructor() {

    fun transformCompanyToPresentationModel(company: Company) = with(company) {
        CompanyPresentationModel(this.name, this.image, this.url)
    }

    fun transformPresentationModelToCompany(companyPresentationModel: CompanyPresentationModel) =
        with(companyPresentationModel) {
            Company(this.name, this.image, this.url)
        }

}
