package hy.uth.btvntuan06.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

/**
 * Model đại diện cho một công việc (Task)
 * Sử dụng Kotlin Serialization để deserialize JSON từ API
 * Các field có thể nullable để tránh lỗi khi API không trả về đầy đủ
 */
@Serializable
data class Task(
    val id: Int,
    val title: String,
    val description: String? = null, // Nullable để tránh lỗi khi API không trả về
    val status: String? = null, // Nullable để tránh lỗi khi API không trả về
    val priority: String? = null, // Nullable để tránh lỗi khi API không trả về
    @SerialName("time") val time: String? = null, // Nullable với default value
    // Nếu API trả về field với tên khác, có thể thêm @SerialName
    // Ví dụ: @SerialName("createdAt") val createdAt: String? = null
)

