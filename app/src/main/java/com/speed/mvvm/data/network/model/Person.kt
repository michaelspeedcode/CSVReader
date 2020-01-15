package com.speed.mvvm.data.network.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Person : Parcelable {
    @Expose
    @SerializedName("First name")
    var firstName: String
        private set
    @Expose
    @SerializedName("Sur name")
    var surname: String
        private set
    @Expose
    @SerializedName("Issue count")
    var issueCount: String
        private set
    @Expose
    @SerializedName("Date of birth")
    var dateOfBirth: String
        private set

    protected constructor(`in`: Parcel) {
        firstName = `in`.readString() ?: ""
        surname = `in`.readString() ?: ""
        issueCount = `in`.readString() ?: ""
        dateOfBirth = `in`.readString() ?: ""
    }

    constructor(firstName: String, surname: String, issueCount: String, dateOfBirth: String) {
        this.firstName = firstName
        this.surname = surname
        this.issueCount = issueCount
        this.dateOfBirth = dateOfBirth
    }


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(firstName)
        parcel.writeString(surname)
        parcel.writeString(issueCount)
        parcel.writeString(dateOfBirth)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Person

        if (firstName != other.firstName) return false
        if (surname != other.surname) return false
        if (issueCount != other.issueCount) return false
        if (dateOfBirth != other.dateOfBirth) return false

        return true
    }

    override fun hashCode(): Int {
        var result = firstName.hashCode()
        result = 31 * result + surname.hashCode()
        result = 31 * result + issueCount.hashCode()
        result = 31 * result + dateOfBirth.hashCode()
        return result
    }

    companion object CREATOR : Parcelable.Creator<Person> {
        override fun createFromParcel(parcel: Parcel): Person {
            return Person(parcel)
        }

        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }
    }
}
