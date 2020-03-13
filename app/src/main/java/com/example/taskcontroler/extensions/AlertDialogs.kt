package com.example.taskcontroler.extensions

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.taskcontroler.R

class AlertDialogs {
    var currentDialog : AlertDialog? = null
    fun waiting(context: Context){
        val mDialogView = LayoutInflater.from(context).inflate(R.layout.alert_waiting, null)
        val mBuilder = AlertDialog.Builder(context).setView(mDialogView)
        val  mAlertDialog = mBuilder.show()
        mAlertDialog.setCancelable(false)
        mAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        currentDialog = mAlertDialog
    }
    fun stop(){
        if(currentDialog != null){
            currentDialog?.dismiss()
            currentDialog = null
        }
    }
}