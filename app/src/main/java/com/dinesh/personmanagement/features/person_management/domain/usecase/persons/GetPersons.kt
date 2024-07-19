package com.dinesh.personmanagement.features.person_management.domain.usecase.persons

import com.dinesh.personmanagement.common.error.DataError
import com.dinesh.personmanagement.common.wrapper.Result
import com.dinesh.personmanagement.features.person_management.domain.model.entities.Person
import com.dinesh.personmanagement.features.person_management.domain.repository.PersonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPersons @Inject constructor(
    private val personRepository: PersonRepository
) {
    suspend operator fun invoke(): Result<Flow<List<Person>>, DataError> {
        return personRepository.getPersons()
    }
}