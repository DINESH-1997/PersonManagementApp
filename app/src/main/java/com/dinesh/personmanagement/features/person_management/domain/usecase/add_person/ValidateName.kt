package com.dinesh.personmanagement.features.person_management.domain.usecase.add_person

import com.dinesh.personmanagement.features.person_management.presentation.add_person.TextFieldState
import javax.inject.Inject

class ValidateName @Inject constructor() {
    operator fun invoke(name: TextFieldState): TextFieldState {
        if (name.text.isEmpty()) {
            return name.copy(
                isError = true,
                errorText = "Please Enter name"
            )
        }
        return name
    }
}