package org.srh.helloworld.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.srh.helloworld.model.UserUrlInput

class HomeViewModel:ViewModel() {

    var urlInput= mutableStateOf(UserUrlInput())

    fun isValidUrl(url:String):Boolean{
        return url.startsWith("http://") || url.startsWith("https://")
    }

    fun onUrlChange(newUrl: String){
        urlInput.value=UserUrlInput(newUrl)
    }
}