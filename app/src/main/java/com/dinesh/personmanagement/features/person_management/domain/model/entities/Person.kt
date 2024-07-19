package com.dinesh.personmanagement.features.person_management.domain.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(
    @PrimaryKey val id: String,
    val name: String,
    val email: String,
    val mobile: String,
    val gender: String,
)
