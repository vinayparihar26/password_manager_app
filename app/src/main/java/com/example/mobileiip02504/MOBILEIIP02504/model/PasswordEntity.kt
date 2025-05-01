package com.example.mobileiip02504.MOBILEIIP02504.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "password_table")
data class  PasswordEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val account: String,
    val username: String,
    val password: String
)