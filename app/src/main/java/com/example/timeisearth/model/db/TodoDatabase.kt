package com.example.timeisearth.model.db

import androidx.room.Database
import androidx.room.Room
import com.example.timeisearth.model.dao.TodoDAO
import com.example.timeisearth.model.entity.Todo
import android.content.Context
import androidx.room.RoomDatabase


@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDAO(): TodoDAO
    companion object {
        var INSTANCE: TodoDatabase? = null
        fun getInstace(context: Context): TodoDatabase? {
            if (INSTANCE == null) {
                synchronized(TodoDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, TodoDatabase::class.java, "user.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}