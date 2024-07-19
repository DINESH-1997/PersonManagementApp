package com.dinesh.personmanagement.features.person_management.presentation.persons.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dinesh.personmanagement.features.person_management.domain.model.entities.Person
import com.dinesh.personmanagement.ui.theme.UserManagementTheme

@Composable
fun PersonCard(
    person: Person
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp)
    ) {
        Row(
            modifier = Modifier.padding(18.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "person"
            )
            Column {
                Text(
                    text = person.name,
                    style = typography.titleMedium
                )
                Text(
                    text = person.email,
                    style = typography.bodyMedium
                )
                Text(
                    text = person.mobile,
                    style = typography.bodyMedium
                )
                Box(
                    modifier = Modifier
                        .background(color = colorScheme.primary, shape = RoundedCornerShape(10.dp))
                        .padding(4.dp)
                ) {
                    Text(
                        person.gender,
                        style = typography.labelSmall,
                        color = colorScheme.onPrimary
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PersonCardPreview() {
    UserManagementTheme {
        PersonCard(
            person = Person(
                id = "1",
                name = "William",
                email = "william@test.com",
                mobile = "4455667788",
                gender = "Male"
            )
        )
    }
}