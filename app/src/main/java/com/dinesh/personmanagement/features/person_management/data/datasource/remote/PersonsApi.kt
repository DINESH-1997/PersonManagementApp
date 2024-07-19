package com.dinesh.personmanagement.features.person_management.data.datasource.remote

import com.dinesh.personmanagement.features.person_management.data.datasource.remote.dto.PersonDto
import com.dinesh.personmanagement.features.person_management.domain.model.requests.Person
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PersonsApi {

    @POST("persons")
    suspend fun addPerson(@Body person: Person): Response<PersonDto>

    @GET("persons")
    suspend fun getPersons(): Response<List<PersonDto>>
}