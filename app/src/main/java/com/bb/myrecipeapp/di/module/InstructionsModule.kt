package com.bb.myrecipeapp.di.module


import com.bb.myrecipeapp.model.Instruction
import com.bb.myrecipeapp.model.PostInstruct
import dagger.Module
import dagger.Provides

@Module
class InstructionsModule {

    constructor()

    @Provides
    fun writeInstruction(): InstructionsModule =
        InstructionsModule()

    @Provides
    fun postInstruction(): PostInstruct {
        return PostInstruct()
    }

    @Provides
    fun createInstruction(instruction : Instruction, postInstruct: PostInstruct): Instruction {
        return Instruction()
    }
    companion object{
        private lateinit var instruction : Instruction
        private lateinit var postInstruct: PostInstruct
    }
}
