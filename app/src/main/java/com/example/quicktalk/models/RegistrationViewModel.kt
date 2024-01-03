package com.example.quicktalk.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quicktalk.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.database.values
import com.google.firebase.ktx.Firebase

class RegistrationViewModel() : ViewModel() {
    private var auth: FirebaseAuth = Firebase.auth
    private val database = Firebase.database
    private val databaseReference = database.getReference("Users")


    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    private val _user: MutableLiveData<FirebaseUser> = MutableLiveData()
    val user: LiveData<FirebaseUser> = _user

    init {
        auth.addAuthStateListener {
            if (it.currentUser != null) {
                _user.value = it.currentUser
            }
        }
    }

    fun signUp(
        email: String,
        password: String,
        name: String? = null,
        lastName: String? = null,
        age: Int? = null
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val firebaseUser = it.user
                val user = User(
                    id = firebaseUser?.uid,
                    name = name,
                    lastName = lastName,
                    age = age,
                    isOnline = false
                )

                user.id?.let { it1 -> databaseReference.child(it1).setValue(user) }

            }
            .addOnFailureListener {
                _error.value = it.message
            }
    }
}