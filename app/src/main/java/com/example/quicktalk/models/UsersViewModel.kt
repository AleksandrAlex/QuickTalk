package com.example.quicktalk.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quicktalk.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UsersViewModel: ViewModel() {
    private var auth: FirebaseAuth = Firebase.auth
    private val database = Firebase.database
    private val databaseReference = database.getReference("Users")

    private val _user: MutableLiveData<FirebaseUser> = MutableLiveData()
    val user: LiveData<FirebaseUser> = _user

    private val _users: MutableLiveData<List<User>> = MutableLiveData()
    val users: LiveData<List<User>> = _users

    init {
        auth.addAuthStateListener {
                _user.value = it.currentUser
        }

        databaseReference.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                val currentUser = auth.currentUser
                val usersFromDB = mutableListOf<User>()


                for (i in snapshot.children){
                        val user = i.getValue<User>()
                    if (user?.id != currentUser?.uid){
                        user?.let { usersFromDB.add(it) }
                    }
                    _users.value = usersFromDB
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun logout(){
        auth.signOut()
    }
}