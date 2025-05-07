package com.example.labs

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ActivityItem::class], version = 3)
@TypeConverters(ActivityTypeConverter::class)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getActivityItemDao() : ActivityItemDao
}