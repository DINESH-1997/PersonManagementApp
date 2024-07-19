package com.dinesh.personmanagement.features.person_management.data.datasource.remote.dto

import com.dinesh.personmanagement.features.person_management.domain.model.entities.Person
import com.google.gson.annotations.SerializedName

data class PersonDto(
    @SerializedName("_id")
    val id: String,
    val name: String,
    val email: String,
    val mobile: String,
    val gender: String
)

fun PersonDto.toPerson(): Person {
    return Person(
        id = id,
        name = name,
        email = email,
        mobile = mobile,
        gender = gender
    )
}