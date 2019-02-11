package domain.companychecker.interactor.company

import domain.companychecker.executor.PostExecutionThread
import domain.companychecker.executor.ThreadExecutor
import domain.companychecker.interactor.SingleUseCase
import domain.companychecker.model.DetailCompany
import domain.companychecker.repository.CompanyRepository
import io.reactivex.Single
import javax.inject.Inject

class GetDetailInfoAboutCompanyUseCase @Inject constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val companyRepository: CompanyRepository
) : SingleUseCase<DetailCompany, String?>(threadExecutor, postExecutionThread) {

    override fun buildUseCase(params: String?): Single<DetailCompany> = companyRepository.getDetailInfo(params)

}
