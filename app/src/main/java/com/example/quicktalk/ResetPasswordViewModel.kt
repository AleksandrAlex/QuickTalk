package com.example.quicktalk

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ResetPasswordViewModel(): ViewModel() {

    private var auth: FirebaseAuth = Firebase.auth

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    private val _isSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val isSuccess: LiveData<Boolean> = _isSuccess

    fun resetPassword(email: String){
        auth.sendPasswordResetEmail(email)
            .addOnSuccessListener {
                _isSuccess.value = true
            }
            .addOnFailureListener {
                _error.value = it.message
            }
    }
}