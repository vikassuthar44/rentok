package best.vikas.rentok.di

import android.content.Context
import androidx.room.Room
import best.vikas.rentok.BuildConfig
import best.vikas.rentok.db.GitUserDao
import best.vikas.rentok.db.GitUserDatabase
import best.vikas.rentok.db.GitUserRepo
import best.vikas.rentok.db.GitUserRepoImpl
import best.vikas.rentok.network.ApiHelper
import best.vikas.rentok.network.ApiHelperImpl
import best.vikas.rentok.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * This App level module and in this provide like singleton etc.
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun applicationContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClient
                .addInterceptor(loggingInterceptor)
        }
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelperImpl: ApiHelperImpl): ApiHelper = apiHelperImpl


    @Provides
    @Singleton
    fun provideDB(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(context = context, GitUserDatabase::class.java, "gitUser_db").build()

    @Provides
    @Singleton
    fun provideDatabaseDao(gitUserDatabase: GitUserDatabase) = gitUserDatabase.gitUserDao()

    @Provides
    @Singleton
    fun provideTodoRepoImpl(gitUserDao: GitUserDao): GitUserRepo =
        GitUserRepoImpl(gitUserDao = gitUserDao)
}