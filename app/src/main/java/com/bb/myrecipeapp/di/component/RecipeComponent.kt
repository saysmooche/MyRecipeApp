package com.bb.myrecipeapp.di.component

import com.bb.myrecipeapp.di.module.InstructionsModule
import com.bb.myrecipeapp.view.ExistingRecipeActivity
import com.bb.myrecipeapp.view.LoginFragment
import com.bb.myrecipeapp.view.RecipeFragment
import dagger.Component

//interface RecipeComponent {

@Component(modules = [InstructionsModule::class])
interface RecipeComponent {

    fun getRecipe(activity: ExistingRecipeActivity)
    fun inject(activity: RecipeFragment)
    fun inject(activity: LoginFragment)
}





//        @Component.Builder
//        interface Builder {
//            @BindsInstance
//            fun application(application: Application?): Builder?
//            fun build(): RecipeComponent?
//        }

//}