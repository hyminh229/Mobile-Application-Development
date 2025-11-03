package hy.uth.btvntuan06.data.model

import kotlinx.serialization.Serializable

/**
 * Response wrapper từ API
 * API trả về format: { "isSuccess": true, "message": "...", "data": [...] }
 */
@Serializable
data class TaskResponse(
    val isSuccess: Boolean,
    val message: String? = null,
    val data: List<Task>? = null
)

/**
 * Response wrapper cho single task
 */
@Serializable
data class SingleTaskResponse(
    val isSuccess: Boolean,
    val message: String? = null,
    val data: Task? = null
)

