package hy.uth.btvntuan06.data.repository

import hy.uth.btvntuan06.data.api.TaskApiService
import hy.uth.btvntuan06.data.model.Task

/**
 * Repository pattern: tầng trung gian giữa ViewModel và API
 * Xử lý logic nghiệp vụ và error handling
 */
class TaskRepository(
    private val apiService: TaskApiService
) {
    
    /**
     * Lấy tất cả danh sách công việc
     * Extract data từ response wrapper
     */
    suspend fun getAllTasks(): Result<List<Task>> {
        return try {
            val response = apiService.getAllTasks()
            if (response.isSuccess && response.data != null) {
                Result.success(response.data)
            } else {
                Result.failure(Exception(response.message ?: "Failed to get tasks"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    /**
     * Lấy chi tiết công việc theo ID
     * Extract data từ response wrapper
     */
    suspend fun getTaskById(id: Int): Result<Task> {
        return try {
            val response = apiService.getTaskById(id)
            if (response.isSuccess && response.data != null) {
                Result.success(response.data)
            } else {
                Result.failure(Exception(response.message ?: "Failed to get task"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    /**
     * Xóa công việc theo ID
     */
    suspend fun deleteTask(id: Int): Result<Unit> {
        return try {
            apiService.deleteTask(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

