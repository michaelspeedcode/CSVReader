package com.speed.mvvm.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.speed.mvvm.DateUtils
import com.speed.mvvm.R
import com.speed.mvvm.data.network.model.Person

class PersonAdapter internal constructor(private val mListener: OnPersonClickListener) : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    private var mItems = arrayListOf<Person?>()

    fun setItems(items: List<Person?>?) {
        mItems.clear()
        items?.let {
            mItems.addAll(it)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = getItem(position)
        holder.setOnClickListener(person)
        person?.dateOfBirth?.let { holder.setDateOfBirth(it) }
        person?.firstName?.let { holder.setFirstName(it) }
        person?.surname?.let { holder.setSurname(it) }
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    private fun getItem(position: Int): Person? {
        return mItems[position]
    }

    interface OnPersonClickListener {
        fun onPersonClicked(person: Person?)
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val dateOfBirth = itemView.findViewById<TextView>(R.id.dateOfBirth)
        private val surname = itemView.findViewById<TextView>(R.id.surname)
        private val firstName = itemView.findViewById<TextView>(R.id.firstName)

        fun setFirstName(firstName: String?) {
            this.firstName?.text = firstName
        }

        fun setDateOfBirth(dateOfBirth: String?) {
            val date = DateUtils.formatDate(dateOfBirth)
            this.dateOfBirth?.text = date
        }

        fun setSurname(surname: String) {
            this.surname?.text = surname
        }

        fun setOnClickListener(person: Person?) {
            itemView.tag = person
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            mListener.onPersonClicked(view.tag as Person)
        }

    }

}