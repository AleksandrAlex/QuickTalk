package com.example.quicktalk.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistrationViewModel(): ViewModel() {
    private var auth: FirebaseAuth = Firebase.auth


    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    private val _user: MutableLiveData<FirebaseUser> = MutableLiveData()
    val user: LiveData<FirebaseUser> = _user

    init {
        auth.addAuthStateListener {
            if (it.currentUser != null){
                _user.value = it.currentUser
            }
        }
    }

    fun signUp(
        email: String,
        password: String,
        name: String? = null,
        lastName: String? = null,
        age: Int? =null
    ){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnFailureListener {
                _error.value = it.message
            }
    }
}