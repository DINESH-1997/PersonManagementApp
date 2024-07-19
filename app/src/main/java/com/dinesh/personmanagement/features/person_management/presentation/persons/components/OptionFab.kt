package com.dinesh.personmanagement.features.person_management.presentation.persons.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun OptionFab(icon: ImageVector, onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        modifier = Modifier.padding(bottom = 16.dp)
    ) {
        Icon(imageVector = icon, contentDescription = null)
    }
}