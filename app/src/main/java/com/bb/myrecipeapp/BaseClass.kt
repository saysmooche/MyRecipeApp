package com.bb.myrecipeapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

abstract class BaseClass : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun goIngredient(view: View?){
        val intent = Intent(applicationContext, ExistingRecipe::class.java)
        startActivity(intent)
    }
    open fun goCreate(view: View?){
        val intent = Intent(applicationContext, RecyclerViewActivity::class.java)
        startActivity(intent)
    }

    companion object {
        const val LOG_TAG = "TAG_N"
    }
}