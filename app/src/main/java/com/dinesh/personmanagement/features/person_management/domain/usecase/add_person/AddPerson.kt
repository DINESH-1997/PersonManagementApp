package com.dinesh.personmanagement.features.person_management.domain.usecase.add_person

import com.dinesh.personmanagement.common.error.DataError
import com.dinesh.personmanagement.common.wrapper.Result
import com.dinesh.personmanagement.features.person_management.data.datasource.remote.dto.PersonDto
import com.dinesh.personmanagement.features.person_management.domain.model.requests.Person
import com.dinesh.personmanagement.features.person_management.domain.repository.PersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddPerson @Inject constructor(
    private val personRepository: PersonRepository
) {
     operator fun invoke(person: Person): Flow<Result<PersonDto?,  DataError>> = flow {
        when(
            val result = personRepository.addPerson(person)
        ) {
            is Result.Error -> {
                emit(Result.Error(result.error))
            }
            is Result.Success -> {
                emit(Result.Success(result.data))
            }
        }

    }
}