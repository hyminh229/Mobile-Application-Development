package hy.uth.bttltuan06.api

import hy.uth.bttltuan06.model.Task
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface TaskApi {
    @GET("researchUTH/tasks")
    suspend fun getTasks(): List<Task>

    @GET("researchUTH/task/{id}")
    suspend fun getTask(@Path("id") id: Int): Task

    @DELETE("researchUTH/task/{id}")
    suspend fun deleteTask(@Path("id") id: Int): Unit
}

