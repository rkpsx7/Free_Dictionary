package com.dev_akash.freedictionary.feature_dictionary.di

import android.app.Application
import androidx.room.Room
import com.dev_akash.freedictionary.utils.network_utils.NetworkConnectionInterceptor
import com.dev_akash.freedictionary.utils.Constants.BASE_URL
import com.dev_akash.freedictionary.utils.GsonParser
import com.dev_akash.freedictionary.feature_dictionary.data.local.DictionaryDB
import com.dev_akash.freedictionary.feature_dictionary.data.local.WordInfoDao
import com.dev_akash.freedictionary.feature_dictionary.data.remote.DictionaryApi
import com.dev_akash.freedictionary.feature_dictionary.data.repository.WordInfoRepoImpl
import com.dev_akash.freedictionary.feature_dictionary.data.util.TypeConvertors
import com.dev_akash.freedictionary.feature_dictionary.domain.repository.WordInfoRepo
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class WordInfoModule {


    @Provides
    @Singleton
    fun providesWordInfoRepo(
        api: DictionaryApi,
        dao: WordInfoDao
    ): WordInfoRepo {
        return WordInfoRepoImpl(api, dao)
    }


    @Singleton
    @Provides
    fun providesDictionaryApi(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): DictionaryApi {
        return retrofitBuilder.client(okHttpClient).build().create(DictionaryApi::class.java)
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(application: Application): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(NetworkConnectionInterceptor(application))
            .build()
    }

    @Provides
    @Singleton
    fun providesWordInfoDao(dictionaryDB: DictionaryDB) = dictionaryDB.dao


    @Singleton
    @Provides
    fun providesRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    fun providesWordInfoDataBase(application: Application): DictionaryDB {
        return Room.databaseBuilder(
            application,
            DictionaryDB::class.java,
            "FREE_DICTIONARY_DB"
        )
            .addTypeConverter(TypeConvertors(GsonParser(Gson())))
            .build()
    }
}