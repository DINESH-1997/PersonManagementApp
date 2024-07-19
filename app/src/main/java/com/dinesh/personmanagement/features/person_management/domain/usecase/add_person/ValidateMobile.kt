package com.dinesh.personmanagement.features.person_management.domain.usecase.add_person

import com.dinesh.personmanagement.features.person_management.presentation.add_person.TextFieldState
import javax.inject.Inject

class ValidateMobile @Inject constructor() {
    operator fun invoke(mobile: TextFieldState): TextFieldState {
        if (mobile.text.isEmpty()) {
            return mobile.copy(
                isError = true,
                errorText = "Please Enter Mobile Number"
            )
        }

        if (mobile.text.length != 10) {
            return mobile.copy(
                isError = true,
                errorText = "Please Enter Valid Mobile Number"
            )
        }

        return mobile
    }
}