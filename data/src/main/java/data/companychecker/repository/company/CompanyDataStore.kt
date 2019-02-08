package data.companychecker.repository.company

import data.companychecker.entity.CompanyEntity
import io.reactivex.Single


interface CompanyDataStore {

    fun searchCompany(companyName: String): Single<List<CompanyEntity>>

}
