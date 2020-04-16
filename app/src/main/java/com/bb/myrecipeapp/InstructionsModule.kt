package com.bb.myrecipeapp


import dagger.Module
import dagger.Provides

@Module
class InstructionsModule {

    constructor()

    @Provides
    fun writeInstruction(): InstructionsModule = InstructionsModule()

    @Provides
    fun postInstruction(): PostInstruct {
            postInstruct.inflate()
        return postInstruct
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
