package data.companychecker.source.company

import data.companychecker.entity.CompanyEntity
import data.companychecker.entity.DetailCompanyEntity
import data.companychecker.repository.company.CompanyDataStore
import data.companychecker.repository.company.CompanyRemote
import io.reactivex.Single
import javax.inject.Inject


class CompanyRemoteDataStore @Inject constructor(
    private val remote: CompanyRemote
) : CompanyDataStore {

    override fun getDetailInfo(companyName: String): Single<DetailCompanyEntity> = remote.getDetailInfo(companyName)

    override fun searchCompany(companyName: String): Single<List<CompanyEntity>> = remote.searchCompany(companyName)

}
