package data.companychecker.source.company

import data.companychecker.repository.company.CompanyDataStore
import javax.inject.Inject

/**
 * Create an instance of a CompanyDataStore
 */
class CompanyDataStoreFactory @Inject constructor(
    private val remote: CompanyRemoteDataStore
) {

    fun retrieveRemoteDataStore(): CompanyDataStore = remote

}
