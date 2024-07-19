package com.dinesh.personmanagement.features.person_management.presentation.persons

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dinesh.personmanagement.common.wrapper.Result
import com.dinesh.personmanagement.features.person_management.domain.model.entities.Person
import com.dinesh.personmanagement.features.person_management.domain.usecase.persons.GetPersons
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonsViewModel @Inject constructor(
    private val getPersons: GetPersons
): ViewModel() {

    var personsState by mutableStateOf(PersonsState())
        private set

    init {
        getPersonsData()
    }

    private fun getPersonsData() {
        viewModelScope.launch {
            when(val result = getPersons()) {
                is Result.Error -> {}
                is Result.Success -> updatePersonsList(result.data)
            }
        }
    }

    private fun updatePersonsList(persons: Flow<List<Person>>) {
        persons.onEach {
            personsState = personsState.copy(persons = it)
        }.launchIn(viewModelScope)
    }
}