package com.example.taskcontroler.presentation.base

data class IntentEvent(
    val clazz: Class<*>,
    val finishCurrent: Boolean = false
)