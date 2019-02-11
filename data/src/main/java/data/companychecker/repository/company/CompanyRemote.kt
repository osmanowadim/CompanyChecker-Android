package data.companychecker.repository.company

import data.companychecker.entity.CompanyEntity
import data.companychecker.entity.DetailCompanyEntity
import io.reactivex.Single


interface CompanyRemote {

    fun searchCompany(companyName: String): Single<List<CompanyEntity>>

    fun getDetailInfo(companyName: String): Single<DetailCompanyEntity>

}
