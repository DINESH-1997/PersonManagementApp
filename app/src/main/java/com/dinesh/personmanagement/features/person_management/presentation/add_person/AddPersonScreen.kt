package com.dinesh.personmanagement.features.person_management.presentation.add_person

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dinesh.personmanagement.features.person_management.presentation.add_person.components.CustomOutlinedTextField
import com.dinesh.personmanagement.features.person_management.presentation.add_person.components.CustomTitledRadioButton
import com.dinesh.personmanagement.ui.theme.UserManagementTheme
import com.dinesh.usermanagement.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AddPersonScreen(
    addPersonState: AddPersonState,
    onEvent: (AddPersonEvent) -> Unit,
    navController: NavController
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    LaunchedEffect(addPersonState.isSuccessful) {
        if (addPersonState.isSuccessful) {
            navController.navigateUp()
            snackBarHostState.showSnackbar(
                message = "Person added successfully!"
            )
        }
    }

    LaunchedEffect(addPersonState.errorMessage) {
        addPersonState.errorMessage?.let {
            val result = snackBarHostState.showSnackbar(
                message = addPersonState.errorMessage
            )
            when(result) {
                SnackbarResult.Dismissed -> {
                    onEvent(AddPersonEvent.ResetError)
                }
                SnackbarResult.ActionPerformed -> {}
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.add_person),
                        style = typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "back"
                        )
                    }
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
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CustomOutlinedTextField(
                value = addPersonState.name.text,
                isError = addPersonState.name.isError,
                errorText = addPersonState.name.errorText,
                onValueChange = { onEvent(AddPersonEvent.NameChanged(it)) },
                leadingIcon = Icons.Filled.Person,
                label = "Name"
            )
            CustomOutlinedTextField(
                value = addPersonState.email.text,
                isError = addPersonState.email.isError,
                errorText = addPersonState.email.errorText,
                onValueChange = { onEvent(AddPersonEvent.EmailChanged(it)) },
                leadingIcon = Icons.Filled.Email,
                label = "Email"
            )
            CustomOutlinedTextField(
                value = addPersonState.mobile.text,
                isError = addPersonState.mobile.isError,
                errorText = addPersonState.mobile.errorText,
                onValueChange = { onEvent(AddPersonEvent.MobileChanged(it)) },
                leadingIcon = Icons.Filled.Phone,
                label = "Mobile"
            )
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CustomTitledRadioButton(
                    title = stringResource(R.string.male),
                    isSelected = addPersonState.gender == stringResource(R.string.male),
                    onClick = {
                        onEvent(
                            AddPersonEvent.GenderChanged(context.getString(R.string.male))
                        )
                    }
                )
                CustomTitledRadioButton(
                    title = stringResource(R.string.female),
                    isSelected = addPersonState.gender == stringResource(R.string.female),
                    onClick = {
                        onEvent(
                            AddPersonEvent.GenderChanged(context.getString(R.string.female))
                        )
                    }
                )
                CustomTitledRadioButton(
                    title = stringResource(R.string.others),
                    isSelected = addPersonState.gender == stringResource(R.string.others),
                    onClick = {
                        onEvent(
                            AddPersonEvent.GenderChanged(context.getString(R.string.others))
                        )
                    }
                )
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                onClick = { onEvent(AddPersonEvent.AddPerson) }
            ) {
                Text(
                    "Submit",
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun AddPersonPreview() {
    UserManagementTheme {
        AddPersonScreen(
            addPersonState = AddPersonState(),
            onEvent = {},
            navController = rememberNavController()
        )
    }
}