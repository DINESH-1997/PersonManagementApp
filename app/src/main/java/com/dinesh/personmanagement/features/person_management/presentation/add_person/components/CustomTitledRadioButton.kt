package com.dinesh.personmanagement.features.person_management.presentation.add_person.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun CustomTitledRadioButton(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
    ) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    Row(
        modifier = Modifier
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = null,
                role = Role.RadioButton,
            )
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onClick
        )
        Text(
            title,
            modifier = Modifier.padding(12.dp)
        )
    }
}