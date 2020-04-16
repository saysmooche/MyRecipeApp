package com.bb.myrecipeapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.bb.myrecipeapp.model.User
import com.google.firebase.auth.FirebaseAuth


class RecipeViewModel(application: Application) : AndroidViewModel(application){

    private val registrationMLD = MutableLiveData<Boolean>()
    private val loginMLD = MutableLiveData<Boolean>()


    fun getUserLoggedIn(): Boolean? {
        return if (FirebaseAuth.getInstance()
                .currentUser != null && FirebaseAuth.getInstance().currentUser
                .isEmailVerified()
        ) true else false
    }

    fun loginUser(user: User) {
        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(user.userName, user.userPassword)
            .addOnCompleteListener { task ->
                if (task.isComplete && task.isSuccessful) {
                    if (FirebaseAuth.getInstance().currentUser!!.isEmailVerified) {
                        loginMLD.setValue(true)
                    } else Toast.makeText(
                        getApplication(),
                        "Please verify email sent to " + user.userName,
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        getApplication(),
                        "Login failed " + task.exception!!.localizedMessage,
                        Toast.LENGTH_LONG
                    ).show()
                    loginMLD.setValue(false)
                }
            }
    }

    fun getRegistrationStatus(): MutableLiveData<Boolean> {
        return registrationMLD
    }

    fun getLoginStatus(): MutableLiveData<Boolean> {
        return loginMLD
    }

    fun registerUser(user: User) {
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(user.userName, user.userPassword)
            .addOnCompleteListener { task ->
                if (task.isComplete && task.isSuccessful) {
                    Toast.makeText(
                        getApplication(),
                        "User Creation Successful: Verification email sent.",
                        Toast.LENGTH_LONG
                    ).show()
                    FirebaseAuth.getInstance().currentUser!!.sendEmailVerification()
                    registrationMLD.setValue(true)
                } else {
                    Toast.makeText(
                        getApplication(),
                        task.exception!!.localizedMessage,
                        Toast.LENGTH_LONG
                    ).show()
                    registrationMLD.setValue(false)
                }
            }
    }



}