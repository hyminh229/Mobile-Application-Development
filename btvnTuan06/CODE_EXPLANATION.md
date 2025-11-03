# Giải thích Code - UTH SmartTasks

## 📁 Cấu trúc Project

### 1. **Model (Task.kt)**
```kotlin
data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val status: String,
    val priority: String,
    val time: String
)
```
- Sử dụng `@Serializable` để deserialize JSON từ API
- Chứa các trường: id, title, description, status, priority, time

---

### 2. **RetrofitService (TaskApiService.kt)**
- Định nghĩa các API endpoints:
  - `getAllTasks()`: GET danh sách tất cả công việc
  - `getTaskById(id)`: GET chi tiết công việc theo ID
  - `deleteTask(id)`: DELETE công việc theo ID
- Sử dụng Retrofit annotations (`@GET`, `@DELETE`, `@Path`)

---

### 3. **Repository (TaskRepository.kt)**
- Tầng trung gian giữa ViewModel và API
- Xử lý error handling bằng `Result<T>`
- Các method:
  - `getAllTasks()`: Trả về `Result<List<Task>>`
  - `getTaskById(id)`: Trả về `Result<Task>`
  - `deleteTask(id)`: Trả về `Result<Unit>`

---

### 4. **NetworkModule (NetworkModule.kt)**
- Dependency Injection module
- Khởi tạo:
  - Retrofit instance với BASE_URL
  - OkHttpClient với logging interceptor
  - TaskApiService
  - TaskRepository

---

### 5. **ViewModel**

#### **TaskListViewModel.kt**
- Quản lý state của màn hình danh sách
- UI States:
  - `Loading`: Đang tải
  - `Empty`: Danh sách rỗng
  - `Success(tasks)`: Có dữ liệu
  - `Error(message)`: Lỗi
- Method: `loadTasks()` để load danh sách từ API

#### **TaskDetailViewModel.kt**
- Quản lý state của màn hình chi tiết
- UI States: `Loading`, `Success(task)`, `Error(message)`
- Methods:
  - `loadTask(id)`: Load chi tiết công việc
  - `deleteTask(id, onSuccess)`: Xóa công việc

---

### 6. **UI Screens**

#### **ListScreen.kt**
- Màn hình danh sách công việc
- Components:
  - `ListScreen`: Container chính, xử lý các state
  - `TaskList`: LazyColumn hiển thị danh sách
  - `TaskCard`: Card hiển thị thông tin một task
  - `StatusBadge`: Badge trạng thái (completed, in progress, pending)
  - `PriorityBadge`: Badge độ ưu tiên (high, medium, low)
  - `EmptyView`: Hiển thị "No Tasks Yet!" khi rỗng
  - `ErrorView`: Hiển thị lỗi và nút Retry

#### **DetailScreen.kt**
- Màn hình chi tiết công việc
- Components:
  - `DetailScreen`: Container chính với Scaffold
  - `TaskDetailContent`: Hiển thị thông tin chi tiết trong các Card
  - `DetailRow`: Row hiển thị label và value
  - `DeleteButton`: Nút xóa ở bottom bar
  - `ErrorDetailView`: Hiển thị lỗi

---

### 7. **MainActivity.kt**
- Entry point của ứng dụng
- Sử dụng Navigation Compose:
  - Route `"list"`: Màn hình danh sách
  - Route `"detail/{taskId}"`: Màn hình chi tiết với taskId parameter
- Khởi tạo ViewModels với custom Factory
- Xử lý navigation:
  - Click task → navigate to detail
  - Delete task → popBackStack + reload list

---

## 🔄 Flow hoạt động

1. **App khởi động** → MainActivity → NavHost startDestination = "list"
2. **ListScreen hiển thị** → ViewModel.loadTasks() → API getAllTasks()
3. **Click task** → navigate("detail/{taskId}")
4. **DetailScreen hiển thị** → ViewModel.loadTask(id) → API getTaskById(id)
5. **Click Delete** → ViewModel.deleteTask(id) → API deleteTask(id) → popBackStack() + reload list

---

## 🎨 UI Features

- Material 3 Design
- Card layout với elevation
- Color-coded badges cho status và priority
- Empty state handling
- Error handling với retry
- Loading indicators
- Smooth navigation

---

## 📝 Notes

- Sử dụng Kotlin Coroutines với `viewModelScope`
- StateFlow để quản lý UI state
- Result-based error handling
- Clean Architecture: Model → API → Repository → ViewModel → UI

