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
 * ViewModel cho màn hình danh sách công việc
 * Quản lý state và logic của ListScreen
 */
class TaskListViewModel(
    private val repository: TaskRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<TaskListUiState>(TaskListUiState.Loading)
    val uiState: StateFlow<TaskListUiState> = _uiState.asStateFlow()
    
    init {
        loadTasks()
    }
    
    /**
     * Load danh sách công việc từ API
     */
    fun loadTasks() {
        viewModelScope.launch {
            _uiState.value = TaskListUiState.Loading
            repository.getAllTasks()
                .onSuccess { tasks ->
                    _uiState.value = if (tasks.isEmpty()) {
                        TaskListUiState.Empty
                    } else {
                        TaskListUiState.Success(tasks)
                    }
                }
                .onFailure { error ->
                    _uiState.value = TaskListUiState.Error(error.message ?: "Unknown error")
                }
        }
    }
}

/**
 * UI State cho màn hình danh sách
 */
sealed class TaskListUiState {
    data object Loading : TaskListUiState()
    data object Empty : TaskListUiState()
    data class Success(val tasks: List<Task>) : TaskListUiState()
    data class Error(val message: String) : TaskListUiState()
}

