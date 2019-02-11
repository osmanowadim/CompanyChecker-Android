package presentation.companychecker.di.modules

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import data.companychecker.entity.mapper.CompanyEntityDataMapper
import data.companychecker.entity.mapper.DetailCompanyEntityDataMapper
import data.companychecker.executor.JobExecutor
import data.companychecker.remote.impl.CompanyRemoteImpl
import data.companychecker.remote.services.CompanyService
import data.companychecker.repository.company.CompanyDataRepository
import data.companychecker.repository.company.CompanyDataStore
import data.companychecker.repository.company.CompanyRemote
import data.companychecker.source.company.CompanyDataStoreFactory
import data.companychecker.source.company.CompanyRemoteDataStore
import domain.companychecker.executor.PostExecutionThread
import domain.companychecker.executor.ThreadExecutor
import domain.companychecker.repository.CompanyRepository
import domain.companychecker.scopes.PerApplication
import presentation.companychecker.UIThread
import retrofit2.converter.gson.GsonConverterFactory

@Module
open class ApplicationModule {

    @Provides
    @PerApplication
    fun provideContext(application: Application): Context = application

    @Provides
    @PerApplication
    fun provideGson(): Gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()

    @Provides
    @PerApplication
    fun provideGsonFactory(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Provides
    @PerApplication
    fun provideExecutor(): ThreadExecutor = JobExecutor()

    @Provides
    @PerApplication
    fun providePostExecutor(): PostExecutionThread = UIThread()

    @Provides
    @PerApplication
    fun provideCompanyRepository(
        factory: CompanyDataStoreFactory,
        mapperCompany: CompanyEntityDataMapper,
        mapperDetailCompany: DetailCompanyEntityDataMapper
    ): CompanyRepository = CompanyDataRepository(factory, mapperCompany, mapperDetailCompany)

    @Provides
    @PerApplication
    fun provideCompanyRemote(companyService: CompanyService)
            : CompanyRemote = CompanyRemoteImpl(companyService)

    @Provides
    @PerApplication
    fun provideCurrencyDataStore(remote: CompanyRemote)
            : CompanyDataStore = CompanyRemoteDataStore(remote)

}
