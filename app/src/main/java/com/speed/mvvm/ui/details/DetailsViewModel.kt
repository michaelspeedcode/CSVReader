package com.speed.mvvm.ui.details

import android.arch.lifecycle.MutableLiveData
import com.speed.mvvm.data.network.model.Person
import com.speed.mvvm.ui.base.BaseViewModel

class DetailsViewModel(private var person: Person) : BaseViewModel() {
    val personLiveData = MutableLiveData<Person>()

    fun loadPersonData() {
        personLiveData.postValue(person)
    }

}