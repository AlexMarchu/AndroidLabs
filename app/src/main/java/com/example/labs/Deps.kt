package com.example.labs

import android.content.Context
import androidx.room.Room

object Deps {
    lateinit var context: Context
    lateinit var db: MyDatabase

    fun initDatabase() {
        db = Room.databaseBuilder(
            context,
            MyDatabase::class.java,
            "MyDB"
        ).fallbackToDestructiveMigration().build()
    }
}