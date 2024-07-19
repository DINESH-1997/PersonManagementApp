package com.dinesh.personmanagement.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.dinesh.personmanagement.common.Constants
import com.dinesh.personmanagement.common.network.NetworkInfo
import com.dinesh.personmanagement.common.network.NetworkInfoImpl
import com.dinesh.personmanagement.features.person_management.data.datasource.local.PersonsDatabase
import com.dinesh.personmanagement.features.person_management.data.datasource.remote.PersonsApi
import com.dinesh.personmanagement.features.person_management.data.repository.PersonRepositoryImpl
import com.dinesh.personmanagement.features.person_management.domain.repository.PersonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNetworkInfo(
        @ApplicationContext context: Context
    ): NetworkInfo {
        return NetworkInfoImpl(context)
    }

    @Provides
    @Singleton
    fun providePersonsApi(): PersonsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PersonsApi::class.java)
    }

    @Provides
    @Singleton
    fun providePersonRepository(
        networkInfo: NetworkInfo,
        personsApi: PersonsApi,
        personDb: PersonsDatabase
    ): PersonRepository {
        return PersonRepositoryImpl(
            networkInfo, personsApi, personDb.personDao
        )
    }

    @Provides
    @Singleton
    fun providePersonsDatabase(
        app: Application
    ): PersonsDatabase {
        return Room.databaseBuilder(
            app,
            PersonsDatabase::class.java,
            PersonsDatabase.DATABASE_NAME
        ).build()
    }
}