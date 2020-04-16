package com.bb.myrecipeapp

import  android.content.ContentValues.TAG
import android.util.Log
import javax.inject.Inject

class Recipe {
    lateinit var ingredient: Ingredient
    lateinit var instructions: InstructionsModule

    @Inject
    constructor(s: String) {
        this.ingredient = ingredient
        this.instructions = instructions

        fun createRecipe(){
            Log.d(TAG, "Creating Recipe")
        }
    }
}