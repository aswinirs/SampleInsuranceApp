package com.example.sampleinsuranceapp.persistance.async

import android.os.AsyncTask
import com.example.sampleinsuranceapp.models.User
import com.example.sampleinsuranceapp.persistance.UserDao

// class RetrieveUserAsyncTask(dao: UserDao) : AsyncTask<User, Void, User>() {
//    private var userDao = dao
//
//     override fun doInBackground(vararg user: User?): User? {
//         return userDao.authenticateUser(user[0]?.username)
//     }
// }