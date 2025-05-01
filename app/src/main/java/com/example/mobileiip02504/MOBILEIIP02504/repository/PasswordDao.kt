package com.example.mobileiip02504.MOBILEIIP02504.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mobileiip02504.MOBILEIIP02504.model.PasswordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PasswordDao {

    @Query("SELECT * FROM PASSWORD_TABLE")
     fun getAll(): Flow<List<PasswordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(password: PasswordEntity)

    @Update
    suspend fun update(password: PasswordEntity)

    @Delete
    suspend fun delete(password: PasswordEntity)

}