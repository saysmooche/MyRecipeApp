package com.bb.myrecipeapp.util

import com.bb.myrecipeapp.model.Ingredient
import com.bb.myrecipeapp.model.Instruction
import io.reactivex.Observable

class RecipeRepository() {

        var cachedIngredient = emptyList<Ingredient>()
        var cachedInstruction = emptyList<Instruction>()

        fun getIngredients(): Observable<List<Ingredient>> {
            if (cachedIngredient.isEmpty()) {
                return getIngredients()
            }
            else {
                return Observable.just(cachedIngredient)
                    .mergeWith(getIngredients())
            }
        }
    }