package com.bb.myrecipeapp

import android.app.Application
import dagger.BindsInstance
import dagger.Component

interface RecipeComponent {

    @Component(modules = [InstructionsModule::class])
    interface RecipeComponent {

        fun getRecipe()
        fun inject(activity: LoginActivity)

        @Component.Builder
        interface Builder {
            @BindsInstance
            fun application(application: Application?): Builder?
            fun build(): RecipeComponent?
        }
    }
}