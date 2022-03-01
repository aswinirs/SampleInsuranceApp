package com.example.sampleinsuranceapp.persistance

import androidx.lifecycle.LiveData
import com.example.sampleinsuranceapp.models.User

class Repository(private val dao: UserDao?) {
    val allUsers: LiveData<List<User>>? = dao?.getUsers()

    fun registerUser(user: User) {
        dao?.registerUser(user)
    }

    suspend fun loginUser(user: User): LiveData<User>? {
        return dao?.authenticateUser(user.username)
    }
}