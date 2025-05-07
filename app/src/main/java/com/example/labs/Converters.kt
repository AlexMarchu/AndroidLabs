package com.example.labs

import androidx.room.TypeConverter

class ActivityTypeConverter {
    @TypeConverter
    fun fromActivityType(type: ActivityType): String {
        return type.displayName
    }

    @TypeConverter
    fun toActivityType(displayName: String): ActivityType {
        return ActivityType.values().first { it.displayName == displayName }
    }
}