package com.example.mobileiip02504.MOBILEIIP02504.presentation.viewModels

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.mobileiip02504.MOBILEIIP02504.model.PasswordEntity
import com.example.mobileiip02504.MOBILEIIP02504.repository.AppDatabase
import com.example.mobileiip02504.MOBILEIIP02504.repository.PasswordRepository
import com.example.mobileiip02504.MOBILEIIP02504.util.EncryptionUtils
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PasswordViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(
        application, AppDatabase::class.java, "password_db"
    ).build()

    private val repository = PasswordRepository(db.passwordDao())

    val passwords = repository.passwords.map { list ->
        list.map {
            it.copy(password = EncryptionUtils.decrypt(it.password))

        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    var errorMsg = mutableStateOf<String?>(null)

    fun addPassword(account: String, username: String, password: String) {
        viewModelScope.launch {
            try {
                val encrypted = EncryptionUtils.encrypt(password)
                repository.insert(
                    PasswordEntity(
                        account = account,
                        username = username,
                        password = encrypted
                    )
                )
            } catch (e: Exception) {
                errorMsg.value = " Error saving password: ${e.message} "
            }
        }
    }

        fun updatePassword(password: PasswordEntity) {
            viewModelScope.launch {
                try {
                    val encrypted = EncryptionUtils.encrypt(password.password)
                    repository.update(
                        password.copy(password = encrypted)
                    )
                } catch (e: Exception) {
                    errorMsg.value = " Error updating password: ${e.message} "

                }
            }
        }

        fun deletePassword(entity: PasswordEntity) {
            viewModelScope.launch {
                try {
                    repository.delete(entity)
                } catch (e: Exception) {
                    errorMsg.value = "Error deleting password: ${e.message}"
                }
            }

        }
}