package com.speed.mvvm

import com.speed.mvvm.data.network.model.Person
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset

class CSVReader {
    companion object {
        fun createListFromFile(file: InputStream): List<Person>? {
            val list: ArrayList<Person> = ArrayList()

            val reader = BufferedReader(InputStreamReader(file, Charset.forName("UTF-8")))
            reader.readLines().forEach {

                val items = it.split(",")
                try {
                    list.add(Person(replaceQuotationMarks(items[0]),
                            replaceQuotationMarks(items[1]),
                            replaceQuotationMarks(items[2]),
                            replaceQuotationMarks(items[3])))
                } catch (e: IndexOutOfBoundsException) {
                    println("Item was malformed")
                }
            }

            //remove first item with column descriptors
            if (list.size > 0) list.removeAt(0)

            return list
        }

        private fun replaceQuotationMarks(item: String) =
                item.replace("\"", "")
    }
}