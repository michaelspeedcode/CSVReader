package com.speed.mvvm.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.speed.mvvm.CSVReader
import com.speed.mvvm.R
import com.speed.mvvm.bind
import com.speed.mvvm.data.network.model.Person
import com.speed.mvvm.ui.base.BaseActivity
import com.speed.mvvm.ui.details.DetailsActivity.Companion.start
import com.speed.mvvm.ui.main.PersonAdapter.OnPersonClickListener

class MainActivity : BaseActivity<MainViewModel?>(), OnPersonClickListener {

    private val recyclerView by bind<RecyclerView>(R.id.recycler_view)
    private val progressBar by bind<ProgressBar>(R.id.progress_bar)
    private val emptyView by bind<TextView>(R.id.empty_view)
    private var personAdapter: PersonAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        personAdapter = PersonAdapter(this)
        recyclerView.adapter = personAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //Set observers for loading status and person list
        viewModel?.loadingStatusLiveData?.observe(this, LoadingObserver())
        viewModel?.personsLiveData?.observe(this, PersonObserver())

        viewModel?.loadPersonsLocal(R.raw.issues)
    }

    override fun createViewModel(): MainViewModel {
        val factory = MainViewModelFactory()
        return ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
    }

    //Observers
    private inner class LoadingObserver : Observer<Boolean?> {
        override fun onChanged(isLoading: Boolean?) {
            if (isLoading == null) return
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }

    private inner class PersonObserver : Observer<List<Person?>?> {
        override fun onChanged(personList: List<Person?>?) {
            personAdapter?.setItems(personList)
            if (personList?.isEmpty() == true) {
                emptyView.visibility = View.VISIBLE
            } else {
                emptyView.visibility = View.GONE
            }
        }
    }

    override fun onPersonClicked(person: Person?) {
        start(this, person)
    }

}