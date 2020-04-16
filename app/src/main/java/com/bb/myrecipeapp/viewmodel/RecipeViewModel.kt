package com.bb.myrecipeapp.viewmodel

import com.bb.myrecipeapp.util.RecipeRepository
import com.bb.myrecipeapp.model.Ingredient
import com.bb.myrecipeapp.model.Recipe
import dagger.Module
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

    @Singleton
    @Module
    class RecipeViewModel() {
        lateinit var recipeRepository: RecipeRepository

        fun getRecipes(): Observable<List<Recipe>> {
            return recipeRepository.getRecipes()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
        }

        fun getIngredients(): Observable<Ingredient> {
            return recipeRepository.getIngredients()
                .map { Recipe("Ingredients loaded successfully!") }
    }
    }

