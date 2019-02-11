package data.companychecker.repository.company

import data.companychecker.entity.mapper.CompanyEntityDataMapper
import data.companychecker.entity.mapper.DetailCompanyEntityDataMapper
import data.companychecker.source.company.CompanyDataStoreFactory
import domain.companychecker.model.Company
import domain.companychecker.model.DetailCompany
import domain.companychecker.repository.CompanyRepository
import io.reactivex.Single
import javax.inject.Inject


class CompanyDataRepository @Inject constructor(
    private val factory: CompanyDataStoreFactory,
    private val mapperCompany: CompanyEntityDataMapper,
    private val mapperDetailCompany: DetailCompanyEntityDataMapper
) : CompanyRepository {

    override fun getDetailInfo(params: String?): Single<DetailCompany> {
        return params?.let {
            factory.retrieveRemoteDataStore()
                .getDetailInfo(params)
                .flatMap { entity ->
                    return@flatMap Single.create<DetailCompany> { singleEmitter ->
                        singleEmitter.onSuccess(
                            mapperDetailCompany.transformFromEntity(entity)
                        )
                    }
                }
        } ?: Single.create<DetailCompany> { it.onError(Throwable("Request for get detail info about company is null")) }
    }

    override fun searchCompany(params: String?): Single<List<Company>> {
        return params?.let {
            factory.retrieveRemoteDataStore()
                .searchCompany(params)
                .flatMap { entity ->
                    return@flatMap Single.create<List<Company>> { singleEmitter ->
                        singleEmitter.onSuccess(
                            entity.map(
                                mapperCompany::transformFromEntity
                            )
                        )
                    }
                }
        } ?: Single.create<List<Company>> { it.onError(Throwable("Request for search companies is null")) }

    }

}
