package com.dinesh.personmanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dinesh.personmanagement.features.location.presentation.LocationScreen
import com.dinesh.personmanagement.features.person_management.presentation.add_person.AddPersonScreen
import com.dinesh.personmanagement.features.person_management.presentation.add_person.AddPersonViewModel
import com.dinesh.personmanagement.features.person_management.presentation.persons.PersonsScreen
import com.dinesh.personmanagement.features.person_management.presentation.persons.PersonsViewModel
import com.dinesh.personmanagement.ui.theme.UserManagementTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UserManagementTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = PersonsScreen
                ) {
                    composable<AddPersonScreen> {
                        val addPersonViewModel = hiltViewModel<AddPersonViewModel>()
                        AddPersonScreen(
                            addPersonState = addPersonViewModel.addPersonState,
                            onEvent = addPersonViewModel::onEvent,
                            navController = navController
                        )
                    }
                    composable<PersonsScreen> {
                        val personsViewModel = hiltViewModel<PersonsViewModel>()
                        PersonsScreen(
                            personsState = personsViewModel.personsState,
                            navController = navController
                        )
                    }
                    composable<LocationScreen> {
                        LocationScreen()
                    }
                }
            }
        }
    }
}

@Serializable
object AddPersonScreen

@Serializable
object PersonsScreen

@Serializable
object LocationScreen