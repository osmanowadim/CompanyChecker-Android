package presentation.companychecker.mapper

import domain.companychecker.model.Company
import presentation.companychecker.model.CompanyPresentationModel
import javax.inject.Inject

class CompanyPresentationModelMapper @Inject constructor() {

    fun transformRatioToPresentationModel(company: Company) = with(company) {
        CompanyPresentationModel(this.data)
    }

    fun transformPresentationModelToRatio(companyPresentationModel: CompanyPresentationModel) =
        with(companyPresentationModel) {
            Company(this.data)
        }

}