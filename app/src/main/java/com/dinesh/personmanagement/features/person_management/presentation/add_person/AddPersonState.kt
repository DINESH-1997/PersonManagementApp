package com.dinesh.personmanagement.features.person_management.presentation.add_person

data class AddPersonState(
    val name: TextFieldState = TextFieldState(),
    val email: TextFieldState = TextFieldState(),
    val mobile: TextFieldState = TextFieldState(),
    val gender: String? = null,
    val errorMessage: String? = null,
    val isSuccessful: Boolean = false
)
