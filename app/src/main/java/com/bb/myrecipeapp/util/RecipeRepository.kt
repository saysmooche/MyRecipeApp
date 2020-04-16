package com.bb.myrecipeapp.util

import com.bb.myrecipeapp.model.Ingredient
import com.bb.myrecipeapp.model.Instruction
import com.bb.myrecipeapp.model.Recipe
import io.reactivex.Observable

class RecipeRepository() {

        var cachedIngredient = emptyList<Ingredient>()
        var cachedInstruction = emptyList<Instruction>()
        var cachedRecipe = emptyList<Recipe>()

        fun getIngredients(): Observable<List<Ingredient>> {
            if (cachedIngredient.isEmpty()) {
                return getIngredients()
            }
            else {
                return Observable.just(cachedIngredient)
                    .mergeWith(getIngredients())
            }
        }
        fun getInstructions(): Observable<List<Instruction>> {
            if (cachedInstruction.isEmpty()) {
                return getInstructions()
            }
            else {
                return Observable.just(cachedInstruction)
                    .mergeWith(getInstructions())
            }
        }

        fun getRecipes(): Observable<List<Recipe>> {
            if (cachedRecipe.isEmpty()) {
                return getRecipes()
            }
            else {
                return Observable.just(cachedRecipe)
                    .mergeWith(getRecipes())
            }
        }
    }