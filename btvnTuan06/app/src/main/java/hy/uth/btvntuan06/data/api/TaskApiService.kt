package hy.uth.btvntuan06.data.api

import hy.uth.btvntuan06.data.model.SingleTaskResponse
import hy.uth.btvntuan06.data.model.TaskResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * RetrofitService định nghĩa các API endpoints
 * Sử dụng Retrofit annotations để mapping HTTP methods
 */
interface TaskApiService {
    
    /**
     * GET all tasks
     * https://amock.io/api/researchUTH/tasks
     * API trả về: { "isSuccess": true, "data": [...] }
     */
    @GET("tasks")
    suspend fun getAllTasks(): TaskResponse
    
    /**
     * GET task detail by id
     * https://amock.io/api/researchUTH/task/{id}
     * API trả về: { "isSuccess": true, "data": {...} }
     */
    @GET("task/{id}")
    suspend fun getTaskById(@Path("id") id: Int): SingleTaskResponse
    
    /**
     * DELETE task by id
     * https://amock.io/api/researchUTH/task/{id}
     */
    @DELETE("task/{id}")
    suspend fun deleteTask(@Path("id") id: Int): Unit
}

