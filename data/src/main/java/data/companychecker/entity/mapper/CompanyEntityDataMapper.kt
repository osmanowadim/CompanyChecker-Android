package data.companychecker.entity.mapper

import data.companychecker.entity.CompanyEntity
import domain.companychecker.interactor.Params
import domain.companychecker.model.Company
import domain.companychecker.scopes.PerApplication
import javax.inject.Inject

/**
 * Mapper class used to :
 * transformFromEntity {@link [data.companychecker.entity.CompanyEntity]} (in the data layer)
 * to {@link [domain.companychecker.model.Company]} in the domain layer.
 * transformToEntity {@link [domain.companychecker.model.Company]} (in the domain layer)
 * to {@link [data.companychecker.entity.CompanyEntity]} in the data layer.
 */
@PerApplication
class CompanyEntityDataMapper @Inject constructor() {

    /**
     * Transform a {@link [data.companychecker.entity.CompanyEntity]} into an {@link [domain.companychecker.model.Company]}.
     *
     * @param companyEntity Object to be transformed.
     * @return {@link [domain.companychecker.model.Company]} if valid {@link [data.companychecker.entity.CompanyEntity]}.
     */
    fun transformFromEntity(companyEntity: CompanyEntity) = with(companyEntity) {
        Company(this.data)
    }

    /**
     * Transform a {@link [domain.companychecker.model.Company]} into an {@link [data.companychecker.entity.CompanyEntity]}.
     *
     * @param params Object to be transformed.
     * @return {@link [data.companychecker.entity.CompanyEntity]} if valid {@link [domain.companychecker.interactor.Params]}.
     */
    fun transformToEntity(params: Params?) = with(params) {
        CompanyEntity((this as CompanyEntity).data)
    }

}
