package com.dinesh.personmanagement.features.person_management.presentation.add_person

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dinesh.personmanagement.common.error.DataError
import com.dinesh.personmanagement.common.wrapper.Result
import com.dinesh.personmanagement.features.person_management.domain.model.requests.Person
import com.dinesh.personmanagement.features.person_management.domain.usecase.add_person.AddPerson
import com.dinesh.personmanagement.features.person_management.domain.usecase.add_person.ValidateEmail
import com.dinesh.personmanagement.features.person_management.domain.usecase.add_person.ValidateMobile
import com.dinesh.personmanagement.features.person_management.domain.usecase.add_person.ValidateName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AddPersonViewModel @Inject constructor(
    private val addPersonUseCase: AddPerson,
    private val validateNameUseCase: ValidateName,
    private val validateEmailUseCase: ValidateEmail,
    private val validateMobileUseCase: ValidateMobile,
): ViewModel() {

    var addPersonState by mutableStateOf(AddPersonState())
        private set

    fun onEvent(event: AddPersonEvent) {
        when(event) {
            AddPersonEvent.AddPerson -> validateData()
            AddPersonEvent.ResetError -> {
                addPersonState = addPersonState.copy(errorMessage = null)
            }
            is AddPersonEvent.EmailChanged -> updateEmail(event.value)
            is AddPersonEvent.MobileChanged -> updateMobile(event.value)
            is AddPersonEvent.NameChanged -> updateName(event.value)
            is AddPersonEvent.GenderChanged -> updateGender(event.value)
        }
    }

    private fun validateData() {
        val name = validateNameUseCase(addPersonState.name)
        val email = validateEmailUseCase(addPersonState.email)
        val mobile = validateMobileUseCase(addPersonState.mobile)

        val hasError = listOf(
            name,
            email,
            mobile
        ).any { it.isError }

        if (hasError) {
            addPersonState = addPersonState.copy(
                name = name,
                email = email,
                mobile = mobile
            )
            return
        }

        if (addPersonState.gender == null) {
            addPersonState = addPersonState.copy(
                errorMessage = "Please Select Gender"
            )
            return
        }

        addPerson()
    }

    private fun updateGender(value: String) {
        addPersonState = addPersonState.copy(
            gender = value
        )
    }

    private fun updateEmail(newValue: String) {
        addPersonState = addPersonState.copy(
            email = addPersonState.email.copy(
                text = newValue,
                isError = false,
                errorText = null
            )
        )
    }

    private fun updateMobile(newValue: String) {
        addPersonState = addPersonState.copy(
            mobile = addPersonState.mobile.copy(
                text = newValue,
                isError = false,
                errorText = null
            )
        )
    }

    private fun updateName(newValue: String) {
        addPersonState = addPersonState.copy(
            name = addPersonState.name.copy(
                text = newValue,
                isError = false,
                errorText = null
            )
        )
    }

    private fun addPerson() {
        addPersonUseCase(
            Person(
                name = addPersonState.name.text,
                email = addPersonState.email.text,
                mobile = addPersonState.mobile.text,
                gender = addPersonState.gender!!
            )
        ).onEach { result ->
            when(result) {
                is Result.Error -> mapErrorToString(result.error)
                is Result.Success -> {
                    addPersonState = addPersonState.copy(
                        errorMessage = null,
                        isSuccessful = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun mapErrorToString(error: DataError) {
        addPersonState = when(error) {
            is DataError.ApiError -> {
                addPersonState.copy(errorMessage = error.message)
            }

            DataError.Network.NO_INTERNET -> {
                addPersonState.copy(errorMessage = "Please check your internet connection!")
            }
        }
    }
}