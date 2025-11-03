package hy.uth.bttltuan06.repository

import hy.uth.bttltuan06.api.TaskApi
import hy.uth.bttltuan06.model.Task
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TaskRepository {
    private val taskApi: TaskApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://amock.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        taskApi = retrofit.create(TaskApi::class.java)
    }

    suspend fun getTasks(): List<Task> {
        return taskApi.getTasks()
    }

    suspend fun getTask(id: Int): Task {
        return taskApi.getTask(id)
    }

    suspend fun deleteTask(id: Int) {
        taskApi.deleteTask(id)
    }
}

