package com.example.quicktalk.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UsersViewModel: ViewModel() {
    private var auth: FirebaseAuth = Firebase.auth

    private val _user: MutableLiveData<FirebaseUser> = MutableLiveData()
    val user: LiveData<FirebaseUser> = _user

    init {
        auth.addAuthStateListener {
                _user.value = it.currentUser
        }
    }

    fun logout(){
        auth.signOut()
    }
}