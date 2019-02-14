package data.companychecker.remote

import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ServiceProvider @Inject constructor() {

    // Set timeout for connecting and reading from the network
    private val timeout = 45L

    fun <S> provide(serviceClass: Class<S>): S =
        createRetrofit(createClient()).create(serviceClass)

    private fun createRetrofit(client: OkHttpClient) = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .client(client)
        .baseUrl("https://company-checker.herokuapp.com/")
        .build()

    private fun createClient() = okhttp3.OkHttpClient.Builder()
        .addInterceptor(makeApiKeyInterceptor())
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(timeout, TimeUnit.SECONDS)
        .readTimeout(timeout, TimeUnit.SECONDS)
        .build()

    private fun makeApiKeyInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()

            val moreHeaders = request.headers().newBuilder()
                .add("ApiKey", "0a222dc1-0dc5-48e1-9523-d2f49b9d4369")
                .add("platform", "android")
                .add("Content-Type", "application/json")

            chain.proceed(request.newBuilder().headers(moreHeaders.build()).build())
        }
    }

}
