package presentation.companychecker.di.modules

import dagger.Module
import dagger.Provides
import data.companychecker.remote.ServiceProvider
import data.companychecker.remote.services.CompanyService
import domain.companychecker.scopes.PerApplication


@Module
class ApiModule {

    @Provides
    @PerApplication
    fun provideCurrencyService(serviceProvider: ServiceProvider)
            : CompanyService = serviceProvider.provide(CompanyService::class.java)

}
