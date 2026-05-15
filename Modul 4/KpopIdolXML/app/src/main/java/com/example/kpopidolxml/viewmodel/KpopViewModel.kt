package com.example.kpopidolxml.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber
import com.example.kpopidolxml.model.KpopIdol

class KpopViewModel(private val param: String) : ViewModel() {
    private val internalIdolList = MutableStateFlow<List<KpopIdol>>(emptyList())
    val idolList: StateFlow<List<KpopIdol>> = internalIdolList.asStateFlow()

    private val internalNavigateToDetail = MutableStateFlow<KpopIdol?>(null)
    val navigateToDetail: StateFlow<KpopIdol?> = internalNavigateToDetail.asStateFlow()

    private val internalNavigateToUrl = MutableStateFlow<String?>(null)
    val navigateToUrl: StateFlow<String?> = internalNavigateToUrl.asStateFlow()

    fun loadData(data: List<KpopIdol>) {
        internalIdolList.value = data
        Timber.d("Data item masuk ke dalam list dengan parameter: $param")
    }

    fun onDetailClicked(idol: KpopIdol) {
        Timber.d("Tombol Detail ditekan")
        Timber.d("Data dari list yang dipilih berpindah ke halaman Detail: ${idol.nama}")
        internalNavigateToDetail.value = idol
    }

    fun onDetailNavigated() {
        internalNavigateToDetail.value = null
    }

    fun onExplicitIntentClicked(url: String) {
        Timber.d("Tombol Explicit Intent ditekan")
        internalNavigateToUrl.value = url
    }

    fun onExplicitIntentNavigated() {
        internalNavigateToUrl.value = null
    }
}