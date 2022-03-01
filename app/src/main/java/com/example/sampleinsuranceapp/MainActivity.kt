package com.example.sampleinsuranceapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sampleinsuranceapp.models.User
import com.example.sampleinsuranceapp.viewmodels.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var viewModel: ViewModel

    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username: EditText = findViewById(R.id.editTextUsername)
        val password: EditText = findViewById(R.id.editTextPassword)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val registerButton = findViewById<Button>(R.id.registerButton)

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        viewModel.allUsers?.observe(this, Observer { users ->
            // log the cached copy of the users
            users?.let { userList ->
               for (i in userList.indices) {
                   Log.d("Logging user names:", userList[i].username)
               }
            }
        })
       viewModel.userFromDb?.observe(this) { user ->
           user.let {
               if (password.text.toString() == username.text.toString()+"123") {
                   Log.d("Logged in user name:", username.text.toString())
               }
           }
       }

        loginButton.setOnClickListener{
            val user = User(username.text.toString(), password.text.toString())
            try {
                 launch {
                    viewModel.login(user)
                }
                Toast.makeText(this, "Login started::", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                // Intentionally do nothing here, we prevent app crash with try catch block.
                Toast.makeText(this, "Exception printed", Toast.LENGTH_LONG).show()
            }
        }

        registerButton.setOnClickListener{
            val user = User(username.text.toString(), password.text.toString())
            try {
                viewModel.register(user)
                Toast.makeText(this, "Register Successful", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                // Intentionally do nothing here, we prevent app crash with try catch block.
                Toast.makeText(this, "Exception printed", Toast.LENGTH_LONG).show()
            }
        }
    }
}