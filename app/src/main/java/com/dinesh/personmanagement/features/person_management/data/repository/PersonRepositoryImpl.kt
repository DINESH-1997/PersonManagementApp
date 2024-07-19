package com.dinesh.personmanagement.features.person_management.data.repository

import com.dinesh.personmanagement.common.error.DataError
import com.dinesh.personmanagement.common.network.NetworkInfo
import com.dinesh.personmanagement.common.wrapper.Result
import com.dinesh.personmanagement.features.person_management.data.datasource.local.dao.PersonDao
import com.dinesh.personmanagement.features.person_management.data.datasource.remote.PersonsApi
import com.dinesh.personmanagement.features.person_management.data.datasource.remote.dto.PersonDto
import com.dinesh.personmanagement.features.person_management.data.datasource.remote.dto.toPerson
import com.dinesh.personmanagement.features.person_management.domain.model.requests.Person
import com.dinesh.personmanagement.features.person_management.domain.model.entities.Person as PersonEntity
import com.dinesh.personmanagement.features.person_management.domain.repository.PersonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val networkInfo: NetworkInfo,
    private val personsApi: PersonsApi,
    private val personDao: PersonDao
): PersonRepository {
    override suspend fun addPerson(person: Person): Result<PersonDto?, DataError> {
        if (networkInfo.isNetworkAvailable()) {
            val response = personsApi.addPerson(person)
            return if (response.isSuccessful) {
                response.body()?.let { personDto ->
                    personDao.insertPerson(personDto.toPerson())
                }
                Result.Success(response.body())
            } else {
                Result.Error(DataError.ApiError(response.message()))
            }
        } else {
            return Result.Error(DataError.Network.NO_INTERNET)
        }
    }

    override suspend fun getPersons(): Result<Flow<List<PersonEntity>>, DataError> {
        if (networkInfo.isNetworkAvailable()) {
            val response = personsApi.getPersons()
            response.body()?.let { personDtoList ->
                val personsList = personDtoList.map {
                    it.toPerson()
                }
                personDao.insertPersons(personsList)
                return Result.Success(personDao.getPersons())
            }
            return Result.Error(DataError.ApiError(response.message()))
        } else {
            return Result.Success(personDao.getPersons())
        }
    }
}