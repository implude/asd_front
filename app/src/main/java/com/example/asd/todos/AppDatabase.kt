package com.example.asd.todos

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.asd.todos.Todo
import com.example.asd.todos.TodoDao

@Database(entities = [Todo::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}