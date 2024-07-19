package com.dinesh.personmanagement.features.person_management.presentation.add_person

sealed class AddPersonEvent {
    data object AddPerson: AddPersonEvent()
    data object ResetError: AddPersonEvent()
    data class NameChanged(val value: String): AddPersonEvent()
    data class EmailChanged(val value: String): AddPersonEvent()
    data class MobileChanged(val value: String): AddPersonEvent()
    data class GenderChanged(val value: String): AddPersonEvent()
}