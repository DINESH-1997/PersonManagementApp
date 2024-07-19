package com.dinesh.personmanagement.features.person_management.presentation.persons

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dinesh.personmanagement.AddPersonScreen
import com.dinesh.personmanagement.LocationScreen
import com.dinesh.personmanagement.features.person_management.presentation.persons.components.MultiOptionFab
import com.dinesh.personmanagement.features.person_management.presentation.persons.components.PersonCard
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonsScreen(
    personsState: PersonsState,
    navController: NavController
) {
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        floatingActionButton = {
            MultiOptionFab(
                onAdd = {
                    navController.navigate(AddPersonScreen)
                },
                onLocation = {
                    navController.navigate(LocationScreen)
                }
            )
        },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Persons",
                        style = typography.titleLarge
                    )
                },
                actions = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "add",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(start = 16.dp, end = 16.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "location",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(start = 16.dp, end = 16.dp)
                    )
                }
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
                snackbar = { Snackbar(snackbarData = it) }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {
            items(personsState.persons) { person ->
                PersonCard(person)
            }
        }
    }
}