package hy.uth.bttltuan06.model

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val status: String,
    val priority: String,
    val category: String,
    val createdAt: String,
    val subtasks: List<Subtask> = emptyList(),
    val attachments: List<Attachment> = emptyList()
)

data class Subtask(
    val id: Int,
    val title: String,
    val completed: Boolean
)

data class Attachment(
    val id: Int,
    val fileName: String,
    val fileUrl: String
)

