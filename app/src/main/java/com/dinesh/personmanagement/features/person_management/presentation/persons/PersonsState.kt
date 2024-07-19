package com.dinesh.personmanagement.features.person_management.presentation.persons

import com.dinesh.personmanagement.features.person_management.domain.model.entities.Person

data class PersonsState(
    val persons: List<Person> = emptyList()
)
