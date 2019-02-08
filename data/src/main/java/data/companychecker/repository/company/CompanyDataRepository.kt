package data.companychecker.repository.company

import data.companychecker.entity.mapper.CompanyEntityDataMapper
import data.companychecker.source.company.CompanyDataStoreFactory
import domain.companychecker.model.Company
import domain.companychecker.repository.CompanyRepository
import io.reactivex.Single
import javax.inject.Inject


class CompanyDataRepository @Inject constructor(
    private val factory: CompanyDataStoreFactory,
    private val mapper: CompanyEntityDataMapper
) : CompanyRepository {

    override fun searchCompany(params: String?): Single<List<Company>> {
        return params?.let {
            factory.retrieveRemoteDataStore()
                .searchCompany(params)
                .flatMap { entity ->
                    return@flatMap Single.create<List<Company>> { singleEmitter ->
                        singleEmitter.onSuccess(
                            entity.map(
                                mapper::transformFromEntity
                            )
                        )
                    }
                }
        } ?: Single.create<List<Company>> { it.onError(Throwable("Request for search companies is null")) }

    }

}
