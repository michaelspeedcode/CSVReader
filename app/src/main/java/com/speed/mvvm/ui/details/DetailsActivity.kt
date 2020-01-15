package com.speed.mvvm.ui.details

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import com.speed.mvvm.DateUtils
import com.speed.mvvm.R
import com.speed.mvvm.bind
import com.speed.mvvm.data.network.model.Person
import com.speed.mvvm.ui.base.BaseActivity

class DetailsActivity : BaseActivity<DetailsViewModel>() {

    private val firstName by bind<TextView>(R.id.firstName)
    private val surname by bind<TextView>(R.id.surname)
    private val dateOfBirth by bind<TextView>(R.id.dateOfBirth)
    private val issues by bind<TextView>(R.id.issues)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        viewModel?.loadPersonData()
        viewModel?.personLiveData?.observe(this, PersonObserver())
    }

    override fun createViewModel(): DetailsViewModel {
        val person: Person = intent.getParcelableExtra(EXTRA_PERSON)
        val factory = DetailsViewModelFactory(person)
        return ViewModelProviders.of(this, factory).get(DetailsViewModel::class.java)
    }

    private inner class PersonObserver : Observer<Person?> {
        override fun onChanged(person: Person?) {
            if (person == null) return
            firstName.text = person.firstName
            surname.text = person.surname
            dateOfBirth.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                DateUtils.formatDate(person.dateOfBirth)
            } else {
                person.dateOfBirth
            }
            issues.text = person.issueCount
        }
    }

    companion object {
        private const val EXTRA_PERSON = "EXTRA_PERSON"
        @JvmStatic
        fun start(context: Context, person: Person?) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(EXTRA_PERSON, person)
            context.startActivity(intent)
        }
    }
}