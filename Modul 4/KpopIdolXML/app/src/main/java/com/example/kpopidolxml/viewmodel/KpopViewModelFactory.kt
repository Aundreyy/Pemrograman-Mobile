package com.example.kpopidolxml.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class KpopViewModelFactory(private val param: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KpopViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return KpopViewModel(param) as T
        }
        throw IllegalArgumentException("ViewModel tidak dikenali")
    }
}