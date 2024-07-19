package com.dinesh.personmanagement.features.person_management.domain.usecase.add_person

import com.dinesh.personmanagement.features.person_management.presentation.add_person.TextFieldState
import javax.inject.Inject

class ValidateEmail @Inject constructor() {
    private val emailRegex = Regex(
        "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"
    )
    operator fun invoke(email: TextFieldState): TextFieldState {
        if (email.text.isEmpty()) {
            return email.copy(
                isError = true,
                errorText = "Please Enter Email"
            )
        }

        if (!emailRegex.matches(email.text)) {
            return email.copy(
                isError = true,
                errorText = "Please Enter Valid Email"
            )
        }

        return email
    }
}