package com.example.labs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.launch
import java.sql.Date

class MyVM : ViewModel() {
    private val db = Deps.db

    val allActivities = db.getActivityItemDao().getAllActivities()

    fun addMyActivity(typeActivity: String) {
        viewModelScope.launch {
            val activity = ActivityItem(
                user = "me",
                type =
                when (typeActivity) {
                    "Велосипед" -> ActivityType.BICYCLE
                    "Бег" -> ActivityType.RUNNING
                    "Шаг" -> ActivityType.WALKING
                    else -> ActivityType.WALKING
                },
                startTime = System.currentTimeMillis(),
                endTime = System.currentTimeMillis() + 500000
            )
            db.getActivityItemDao().insert(activity)
        }
    }
}