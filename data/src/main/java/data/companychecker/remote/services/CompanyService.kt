package data.companychecker.remote.services

import data.companychecker.entity.CompanyEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CompanyService {

    @GET("/api/company/search")
    fun searchCompany(@Query("term") companyName: String): Single<List<CompanyEntity>>

}
