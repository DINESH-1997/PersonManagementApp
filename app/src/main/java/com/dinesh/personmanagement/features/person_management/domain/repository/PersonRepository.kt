package com.dinesh.personmanagement.features.person_management.domain.repository

import com.dinesh.personmanagement.common.error.DataError
import com.dinesh.personmanagement.common.wrapper.Result
import com.dinesh.personmanagement.features.person_management.data.datasource.remote.dto.PersonDto
import com.dinesh.personmanagement.features.person_management.domain.model.requests.Person
import com.dinesh.personmanagement.features.person_management.domain.model.entities.Person as PersonEntity
import kotlinx.coroutines.flow.Flow

interface PersonRepository {

    suspend fun addPerson(person: Person): Result<PersonDto?, DataError>

    suspend fun getPersons(): Result<Flow<List<PersonEntity>>, DataError>
}