package com.bb.myrecipeapp

import dagger.Module
import java.util.*
import javax.inject.Singleton

    @Singleton
    @Module
    class RecipeViewModel() {
        lateinit var recipeRepository: RecipeRepository

        fun getRecipes(): Observable<Recipe> {
            return recipeRepository.getIngredients()
                .map { Recipe("Recipe loaded successfully!") }
        }

        fun getIngredients(): Observable<Ingredient> {
            return recipeRepository.getIngredients()
                .map { Recipe("Ingredients loaded successfully!") }
    }
    }

