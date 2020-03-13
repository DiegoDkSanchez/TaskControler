package com.example.core.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

class LiveMessageEvent<T> : SingleLiveEvent<(T.() -> Unit)?>() {

    fun setEventReceiver(owner: LifecycleOwner, receiver: T){
        observe(owner, Observer { event ->
            if(event != null){
                receiver.event()
            }
        })
    }

    fun sentEvent(event: (T.() -> Unit)?) {
        value = event
    }
}