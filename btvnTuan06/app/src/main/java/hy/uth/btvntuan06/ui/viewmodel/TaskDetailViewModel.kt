package hy.uth.btvntuan06.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hy.uth.btvntuan06.data.model.Task
import hy.uth.btvntuan06.data.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel cho màn hình chi tiết công việc
 * Quản lý state và logic của DetailScreen
 */
class TaskDetailViewModel(
    private val repository: TaskRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<TaskDetailUiState>(TaskDetailUiState.Loading)
    val uiState: StateFlow<TaskDetailUiState> = _uiState.asStateFlow()
    
    /**
     * Load chi tiết công việc theo ID
     */
    fun loadTask(taskId: Int) {
        viewModelScope.launch {
            _uiState.value = TaskDetailUiState.Loading
            repository.getTaskById(taskId)
                .onSuccess { task ->
                    _uiState.value = TaskDetailUiState.Success(task)
                }
                .onFailure { error ->
                    _uiState.value = TaskDetailUiState.Error(error.message ?: "Unknown error")
                }
        }
    }
    
    /**
     * Xóa công việc
     */
    fun deleteTask(taskId: Int, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _uiState.value = TaskDetailUiState.Loading
            repository.deleteTask(taskId)
                .onSuccess {
                    onSuccess()
                }
                .onFailure { error ->
                    _uiState.value = TaskDetailUiState.Error(error.message ?: "Delete failed")
                }
        }
    }
}

/**
 * UI State cho màn hình chi tiết
 */
sealed class TaskDetailUiState {
    data object Loading : TaskDetailUiState()
    data class Success(val task: Task) : TaskDetailUiState()
    data class Error(val message: String) : TaskDetailUiState()
}

