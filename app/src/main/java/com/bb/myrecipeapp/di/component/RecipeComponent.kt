package com.bb.myrecipeapp.di.component

import com.bb.myrecipeapp.di.module.InstructionsModule
import com.bb.myrecipeapp.view.LoginActivity
import dagger.Component

//interface RecipeComponent {

@Component(modules = [InstructionsModule::class])
interface RecipeComponent {

    fun getRecipe()
    fun inject(activity: LoginActivity)

//        @Component.Builder
//        interface Builder {
//            @BindsInstance
//            fun application(application: Application?): Builder?
//            fun build(): RecipeComponent?
//        }
}
//}