package com.dinesh.personmanagement.features.person_management.presentation.persons

sealed class PersonsEvent {
    data object GetPersons: PersonsEvent()
}