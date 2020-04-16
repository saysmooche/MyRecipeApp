package com.bb.myrecipeapp

import java.util.*

    class RecipeRepository(var recipeRepository: RecipeRepository,
                           var instructionsModule: InstructionsModule) {

        lateinit var ing : Ing
        var cachedIngredient = emptyList<Ingredient>()
        var cachedInstruction = emptyList<Instruction>()

        fun getIngredients(): Observable<List<Ingredient>> {
            if (cachedIngredient.isEmpty()) {
                return recipeRepository.getIngredients()
                    .doOnNext { cachedIngredient = ing }
            }
            else {
                return Observable.just(cachedIngredient)
                    .mergeWith(recipeRepository.getIngredients())
                    .doOnNext { cachedIngredient = ing }
            }
        }
    }