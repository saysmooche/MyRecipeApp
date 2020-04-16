package com.bb.myrecipeapp.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bb.myrecipeapp.view.ExistingRecipeActivity
import com.bb.myrecipeapp.view.IngredientViewActivity

abstract class BaseClass : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun goIngredient(view: View?){
        val intent = Intent(applicationContext, ExistingRecipeActivity::class.java)
        startActivity(intent)
    }
    open fun goCreate(view: View?){
        val intent = Intent(applicationContext, IngredientViewActivity::class.java)
        startActivity(intent)
    }

    companion object {
        const val LOG_TAG = "TAG_N"
    }
}