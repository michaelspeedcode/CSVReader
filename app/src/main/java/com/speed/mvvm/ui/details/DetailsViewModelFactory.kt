package com.speed.mvvm.ui.details

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.speed.mvvm.data.network.model.Person

class DetailsViewModelFactory internal constructor(private val person: Person) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(person) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}