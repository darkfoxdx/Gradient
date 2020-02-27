package com.projecteugene.gradient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Eugene Low
 */
class MainActivityViewModel: ViewModel() {
    val color1: MutableLiveData<String> = MutableLiveData()
    val color2: MutableLiveData<String> = MutableLiveData()
}