package com.speed.mvvm.ui.main

import android.arch.lifecycle.MutableLiveData
import com.speed.mvvm.CSVApp
import com.speed.mvvm.CSVReader
import com.speed.mvvm.data.network.model.Person
import com.speed.mvvm.ui.base.BaseViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {
    val personsLiveData: MutableLiveData<List<Person?>?> = MutableLiveData()
    val loadingStatusLiveData: MutableLiveData<Boolean?> = MutableLiveData()

    private fun setPersons(persons: List<Person>?) {
        setIsLoading(false)
        this.personsLiveData.postValue(persons)
    }

    fun loadPersonsLocal(issues: Int) {
        setIsLoading(true)

        GlobalScope.launch {
            val issuesFile = CSVApp.getInstance().resources.openRawResource(issues)

            val result = async {
                CSVReader.createListFromFile(issuesFile)
            }
            setPersons(result.await())
        }
    }

    private fun setIsLoading(loading: Boolean) {
        loadingStatusLiveData.postValue(loading)
    }

}