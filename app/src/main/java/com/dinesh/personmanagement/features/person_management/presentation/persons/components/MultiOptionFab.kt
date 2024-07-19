package com.dinesh.personmanagement.features.person_management.presentation.persons.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MultiOptionFab(
    onAdd: () -> Unit,
    onLocation: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
        Column(horizontalAlignment = Alignment.End) {
            if (expanded) {
                OptionFab(
                    icon = Icons.Default.Add,
                    onClick = onAdd
                )
                OptionFab(
                    icon = Icons.Default.LocationOn,
                    onClick = onLocation
                )
            }
            FloatingActionButton(
                onClick = { expanded = !expanded },
            ) {
                Icon(
                    imageVector = if (expanded) Icons.Default.Clear else Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    }
}