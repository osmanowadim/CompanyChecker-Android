package domain.companychecker.repository

import domain.companychecker.model.Company
import io.reactivex.Single

/**
 * Interface that represents a Repository for getting {@link [domain.companychecker.model.Company]} related data.
 */
interface CompanyRepository {

    fun searchCompany(params: String?): Single<List<Company>>

}
