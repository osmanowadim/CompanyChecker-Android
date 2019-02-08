package domain.companychecker.interactor.company

import domain.companychecker.executor.PostExecutionThread
import domain.companychecker.executor.ThreadExecutor
import domain.companychecker.interactor.SingleUseCase
import domain.companychecker.model.Company
import domain.companychecker.repository.CompanyRepository
import io.reactivex.Single
import javax.inject.Inject

class SearchCompaniesUseCase @Inject constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val companyRepository: CompanyRepository
) : SingleUseCase<List<Company>, String?>(threadExecutor, postExecutionThread) {

    override fun buildUseCase(params: String?): Single<List<Company>> = companyRepository.searchCompany(params)

}