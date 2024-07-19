package com.dinesh.personmanagement.features.person_management.presentation.add_person

data class TextFieldState(
    val text: String = "",
    val isError: Boolean = false,
    val errorText: String? = null
)
