package com.dinesh.personmanagement.features.person_management.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dinesh.personmanagement.features.person_management.domain.model.entities.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(person: Person)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPersons(persons: List<Person>)

    @Query("select  * from person")
    fun getPersons(): Flow<List<Person>>
}