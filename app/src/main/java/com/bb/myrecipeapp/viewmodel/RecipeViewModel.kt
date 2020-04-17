package com.bb.myrecipeapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.bb.myrecipeapp.model.Ingredient
import com.bb.myrecipeapp.model.Instruction
import com.bb.myrecipeapp.model.Recipe
import com.bb.myrecipeapp.model.User
import com.bb.myrecipeapp.util.RecipeRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RecipeViewModel(application: Application) : AndroidViewModel(application){

    companion object{
        private val registrationMLD = MutableLiveData<Boolean>()
        private val loginMLD = MutableLiveData<Boolean>()
        lateinit var recipeRepository: RecipeRepository
        private lateinit var recipeReference: DatabaseReference

    }

    fun getUserLoggedIn(): Boolean? {
        return FirebaseAuth.getInstance()
            .currentUser != null && FirebaseAuth.getInstance().currentUser
            ?.isEmailVerified ?: true
    }

    fun loginUser(user: User) {
        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(user.userName, user.userPassword)
            .addOnCompleteListener { task ->
                if (task.isComplete && task.isSuccessful) {
                    FirebaseAuth.getInstance().currentUser?.let { currentUser->
                            currentUser.isEmailVerified;
                        }
                        loginMLD.setValue(true)
                    Toast.makeText(
                        getApplication(),
                        "Please verify email sent to " + user.userName,
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        getApplication(),
                        "Login failed " + task.exception?.localizedMessage,
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
                    FirebaseAuth.getInstance().currentUser?.let {currentUser->
                        currentUser.sendEmailVerification() }
                    registrationMLD.setValue(true)
                } else {
                    Toast.makeText(
                        getApplication(),
                        task.exception?.localizedMessage,
                        Toast.LENGTH_LONG
                    ).show()
                    registrationMLD.setValue(false)
                }
            }
    }

    fun getIngredients(): Observable<List<Ingredient>>? {
        return recipeRepository.getIngredients()
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(Schedulers.io())
    }

    fun getInstructions(): Observable<List<Instruction>> {
        return recipeRepository.getInstruction()
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(Schedulers.io())
    }

    interface RecipeComponent {

        fun getRecipe(): Observable<List<Recipe>>
    }
}




