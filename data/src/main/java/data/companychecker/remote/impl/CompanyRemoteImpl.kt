package data.companychecker.remote.impl

import data.companychecker.entity.CompanyEntity
import data.companychecker.entity.DetailCompanyEntity
import data.companychecker.remote.services.CompanyService
import data.companychecker.repository.company.CompanyRemote
import io.reactivex.Single
import javax.inject.Inject


class CompanyRemoteImpl @Inject constructor(
    private val companyService: CompanyService
) : CompanyRemote {

    override fun getDetailInfo(companyName: String): Single<DetailCompanyEntity> {
        return companyService.getDetailInfo(companyName, "reviews,vacancies")
    }

    override fun searchCompany(companyName: String): Single<List<CompanyEntity>> {
        return companyService.searchCompany(companyName)
    }

}
