package data.companychecker.repository.company

import data.companychecker.entity.CompanyEntity
import io.reactivex.Single


interface CompanyRemote {

    fun searchCompany(companyName: String): Single<List<CompanyEntity>>

}
