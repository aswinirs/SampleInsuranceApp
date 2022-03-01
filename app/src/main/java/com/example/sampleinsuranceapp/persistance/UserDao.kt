package com.example.sampleinsuranceapp.persistance

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.sampleinsuranceapp.models.User

@Dao
interface UserDao {
    @Query("Select * from users ORDER BY username ASC")
    fun getUsers(): LiveData<List<User>>?

    @Query("Select * from users WHERE username = :username LIMIT 1")
    fun authenticateUser(username: String?): LiveData<User>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun registerUser(vararg user: User?)
}
//
//    @Delete
//    fun delete(user: User)
//
//    @Query("SELECT * FROM users")
//    fun getAll(): List<User>
