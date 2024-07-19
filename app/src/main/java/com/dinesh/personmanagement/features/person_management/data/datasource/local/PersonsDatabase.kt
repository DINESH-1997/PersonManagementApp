package com.dinesh.personmanagement.features.person_management.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dinesh.personmanagement.features.person_management.data.datasource.local.dao.PersonDao
import com.dinesh.personmanagement.features.person_management.domain.model.entities.Person

@Database(
    entities = [Person::class],
    version = 1
)
abstract class PersonsDatabase: RoomDatabase() {

    abstract val personDao: PersonDao

    companion object {
        const val DATABASE_NAME = "persons_db"
    }
}