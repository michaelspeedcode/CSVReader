package com.speed.mvvm

import android.os.Build
import android.support.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateUtils {
    companion object {

        @RequiresApi(Build.VERSION_CODES.O)
        fun formatDate(dateOfBirth: String?): String {
            try {
                if (dateOfBirth.isNullOrBlank()) return ""

                val localDateTime: LocalDateTime = LocalDateTime.parse(dateOfBirth)
                val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy '-' hh:mma")
                return formatter.format(localDateTime)
            } catch (e: Throwable) {
                return dateOfBirth ?: ""
            }
        }
    }

}