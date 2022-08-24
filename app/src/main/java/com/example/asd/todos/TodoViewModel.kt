package com.example.asd.todos

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.*
import androidx.room.Room

class TodoViewModel(context: Context) : ViewModel() {

    private val todoDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "todo")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    private val todoDao = todoDatabase.todoDao()
    private val todos = todoDao.getAll()
    val mutableLiveData = MutableLiveData<MutableList<Todo>>(todos)

    fun getList(): MutableList<Todo> {
        return getAll()
    }

    fun getAll() : MutableList<Todo> {
        return todoDao.getAll()
    }
    fun insert(todo: Todo) {
        todoDao.insert(todo)
    }
    fun delete(todo: Todo) {
        todoDao.delete(todo)
    }



}