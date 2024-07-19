package com.dinesh.personmanagement.features.person_management.presentation.add_person.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: ImageVector,
    label: String,
    isError: Boolean = false,
    errorText: String? = null
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        isError = isError,
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = "field-leading-icon"
            )
        },
        supportingText = {
            if (errorText != null) {
                Text(
                    errorText,
                    textAlign = TextAlign.End,
                    color = colorScheme.onSurfaceVariant,
                    style = typography.labelSmall
                )
            }
        },
        label = {
            Text(label)
        }
    )
}