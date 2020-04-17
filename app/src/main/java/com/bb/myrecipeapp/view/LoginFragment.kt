package com.bb.myrecipeapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bb.myrecipeapp.R
import com.bb.myrecipeapp.model.User
import com.bb.myrecipeapp.util.DebugLogger
import com.bb.myrecipeapp.viewmodel.RecipeViewModel
import kotlinx.android.synthetic.main.login_fragment_layout.*


class LoginFragment: Fragment() {

    private lateinit var viewModel: RecipeViewModel
    private lateinit var registeredObserver: Observer<Boolean>
    private lateinit var loginObserver: Observer<Boolean>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registeredObserver = Observer { aBoolean -> handleRegistration(aBoolean) }
        loginObserver = Observer { aBoolean -> handleLogin(aBoolean) }
        viewModel = ViewModelProviders.of(this).get(RecipeViewModel::class.java)

    }

    private fun handleLogin(aBoolean: Boolean) {
        if(aBoolean){
            DebugLogger.logDebug(getString(R.string.success_login))
            ((activity as ExistingRecipeActivity?)?.loginSuccess())
        }
    }

    private fun handleRegistration(aBoolean: Boolean) {
        if (aBoolean) {
            sign_up_layout.setVisibility(View.GONE)
            Toast.makeText(context, getString(R.string.ck_verify_email_message), Toast.LENGTH_LONG).show()
        }
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.login_button ->                 //Attempt login
                userLogin()
            R.id.sign_up_button ->                 //Attempt registration
                userRegister()
            R.id.sign_up_textview ->                 //Attempt registration
                sign_up_layout.setVisibility(View.VISIBLE)
        }
    }

    private fun userLogin() {
        val userName: String = user_name_edittext.text.toString().trim()
        val passWord: String = password_edittext.text.toString().trim()
        val loginUser = User(userName, passWord)
        viewModel.loginUser(loginUser)
        this.loginObserver?.let { viewModel.getLoginStatus().observe(this, it) }
    }

    private fun userRegister() {
        val userName: String = user_name_edittext2.text.toString().trim()
        val passWord: String = password_edittext2.text.toString().trim()
        val regUser = User(userName, passWord)
        viewModel.loginUser(regUser)
        this.loginObserver?.let { viewModel.getLoginStatus().observe(this, it) }

    }
}