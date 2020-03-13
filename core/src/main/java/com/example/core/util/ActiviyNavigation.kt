package com.example.core.util

import android.content.Intent

interface ActiviyNavigation {
    fun startActivityForResult(intent: Intent?, requestCode: Int)
}