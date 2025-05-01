package com.example.mobileiip02504.MOBILEIIP02504.repository

import com.example.mobileiip02504.MOBILEIIP02504.model.PasswordEntity
import kotlinx.coroutines.flow.Flow

class PasswordRepository(private val dao: PasswordDao) {
    val passwords: Flow<List<PasswordEntity>> = dao.getAll()


    suspend fun insert(password: PasswordEntity) {
        dao.insert(password)
    }

    suspend fun update(password: PasswordEntity) {
        dao.update(password)
    }

    suspend fun delete(password: PasswordEntity) {
        dao.delete(password)
    }
}