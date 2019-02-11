package data.companychecker.remote.services

import data.companychecker.entity.CompanyEntity
import data.companychecker.entity.DetailCompanyEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CompanyService {

    @GET("/api/company/search")
    fun searchCompany(@Query("term") companyName: String): Single<List<CompanyEntity>>

    @GET("/api/getinfo")
    fun getDetailInfo(@Query("cname") companyName: String, @Query("expand") expand: String): Single<DetailCompanyEntity>

}
