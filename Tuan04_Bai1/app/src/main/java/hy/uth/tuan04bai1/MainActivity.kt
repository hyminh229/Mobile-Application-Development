package hy.uth.tuan04bai1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hy.uth.tuan04bai1.ui.theme.Tuan04bai1Theme

// --------- DATA MODELS ----------
data class Book(val id: String, val title: String)
data class Student(
    val id: String,
    val name: String,
    val borrowed: MutableSet<String> = mutableSetOf() // set bookId
)

// --------- SAMPLE DATA ----------
private fun sampleBooks() = listOf(
    Book("b1", "Sách 01"),
    Book("b2", "Sách 02"),
    Book("b3", "Sách 03"),
    Book("b4", "Sách 04")
)

private fun sampleStudents() = listOf(
    Student("s1", "Nguyen Van A", mutableSetOf("b1", "b2")),
    Student("s2", "Nguyen Thi B", mutableSetOf("b1")),
    Student("s3", "Nguyen Van C")
)

// --------- ENTRY ----------
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { Tuan04bai1Theme { LibraryApp() } }
    }
}

// --------- APP SHELL ----------
enum class Tab { Manage, Books, Students }

@Composable
fun LibraryApp() {
    // app states (in-memory)
    var tab by remember { mutableStateOf(Tab.Manage) }
    val books = remember { mutableStateListOf<Book>().apply { addAll(sampleBooks()) } }
    val students = remember { mutableStateListOf<Student>().apply { addAll(sampleStudents()) } }
    var currentStudentIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = tab == Tab.Manage,
                    onClick = { tab = Tab.Manage },
                    label = { Text("Quản lý") },
                    icon = { Icon(Icons.Default.Home, null) }
                )
                NavigationBarItem(
                    selected = tab == Tab.Books,
                    onClick = { tab = Tab.Books },
                    label = { Text("DS Sách") },
                    icon = { Icon(Icons.Default.Book, null) }
                )
                NavigationBarItem(
                    selected = tab == Tab.Students,
                    onClick = { tab = Tab.Students },
                    label = { Text("Sinh viên") },
                    icon = { Icon(Icons.Default.Person, null) }
                )
            }
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = "Hệ thống\nQuản lý Thư viện",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 26.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))

            when (tab) {
                Tab.Manage -> ManageScreen(
                    students = students,
                    currentIndex = currentStudentIndex,
                    onChangeStudent = { currentStudentIndex = it },
                    books = books
                )
                Tab.Books -> BookListScreen(books)
                Tab.Students -> StudentListScreen(students, books)
            }
        }
    }
}

// --------- SCREENS ----------

@Composable
private fun ManageScreen(
    students: MutableList<Student>,
    currentIndex: Int,
    onChangeStudent: (Int) -> Unit,
    books: List<Book>
) {
    val current = students.getOrNull(currentIndex) ?: return

    // ---- selector ----
    Text("Sinh viên", fontWeight = FontWeight.SemiBold)
    Spacer(Modifier.height(6.dp))

    var expand by remember { mutableStateOf(false) }
    Row(verticalAlignment = Alignment.CenterVertically) {
        OutlinedTextField(
            value = current.name,
            onValueChange = {},
            modifier = Modifier.weight(1f),
            readOnly = true,
            singleLine = true,
            shape = RoundedCornerShape(12.dp)
        )
        Spacer(Modifier.width(8.dp))
        Button(
            onClick = { expand = true },
            shape = RoundedCornerShape(12.dp)
        ) { Text("Thay đổi") }

        DropdownMenu(expanded = expand, onDismissRequest = { expand = false }) {
            students.forEachIndexed { i, st ->
                DropdownMenuItem(
                    text = { Text(st.name) },
                    onClick = {
                        onChangeStudent(i)
                        expand = false
                    }
                )
            }
        }
    }

    Spacer(Modifier.height(16.dp))
    Text("Danh sách sách", fontWeight = FontWeight.SemiBold)
    Spacer(Modifier.height(8.dp))

    val hasBorrowed = current.borrowed.isNotEmpty()

    Surface(
        color = Color(0xFFF1F1F5),
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        if (!hasBorrowed) {
            Column(
                Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Bạn chưa mượn quyển sách nào", fontWeight = FontWeight.Medium)
                Text("Nhấn 'Thêm' để bắt đầu hành trình đọc sách!",
                    color = Color.Gray, fontSize = 13.sp)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 14.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                val borrowedBooks = books.filter { current.borrowed.contains(it.id) }
                items(borrowedBooks, key = { it.id }) { b ->
                    BorrowedBookCard(
                        title = b.title,
                        checked = true,
                        onCheckedChange = {
                            // uncheck = trả sách
                            current.borrowed.remove(b.id)
                        }
                    )
                }
            }
        }
    }

    Spacer(Modifier.height(16.dp))
    // ---- Add more books for this student ----
    var showPicker by remember { mutableStateOf(false) }
    Button(
        onClick = { showPicker = true },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(14.dp)
    ) { Text("Thêm") }

    if (showPicker) {
        AddBooksDialog(
            allBooks = books,
            currentBorrowed = current.borrowed,
            onDismiss = { showPicker = false },
            onSave = { selected ->
                current.borrowed.addAll(selected)
                showPicker = false
            }
        )
    }
}

@Composable
private fun BookListScreen(books: MutableList<Book>) {
    Spacer(Modifier.height(6.dp))
    ElevatedCard(Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)) {
        Column(Modifier.padding(16.dp)) {
            Text("Danh sách Sách", fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(8.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(books, key = { it.id }) { b ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .shadow(3.dp, RoundedCornerShape(14.dp))
                            .background(Color.White, RoundedCornerShape(14.dp))
                            .padding(horizontal = 14.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            Modifier
                                .size(26.dp)
                                .background(Color(0xFFE8F0FE), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Book, contentDescription = null, tint = Color(0xFF1A73E8))
                        }
                        Spacer(Modifier.width(10.dp))
                        Text(b.title, fontSize = 16.sp)
                    }
                }
            }
        }
    }
}

@Composable
private fun StudentListScreen(students: List<Student>, books: List<Book>) {
    Spacer(Modifier.height(6.dp))
    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(students, key = { it.id }) { st ->
            ElevatedCard(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(st.name, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                    Spacer(Modifier.height(8.dp))
                    val titles = st.borrowed.mapNotNull { id -> books.find { it.id == id }?.title }
                    if (titles.isEmpty()) {
                        Text("Chưa mượn sách nào", color = Color.Gray, fontSize = 13.sp)
                    } else {
                        Text("Đang mượn: ${titles.joinToString()}")
                    }
                }
            }
        }
    }
}

// --------- REUSABLE UI ----------

@Composable
private fun BorrowedBookCard(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .shadow(6.dp, RoundedCornerShape(18.dp))
            .background(Color.White, RoundedCornerShape(18.dp))
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        Spacer(Modifier.width(6.dp))
        Text(title, fontSize = 16.sp)
    }
}

@Composable
private fun AddBooksDialog(
    allBooks: List<Book>,
    currentBorrowed: Set<String>,
    onDismiss: () -> Unit,
    onSave: (Set<String>) -> Unit
) {
    val temp = remember(currentBorrowed) { currentBorrowed.toMutableSet() }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Chọn sách để mượn / trả") },
        text = {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(allBooks, key = { it.id }) { b ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        val ckd = temp.contains(b.id)
                        Checkbox(checked = ckd, onCheckedChange = {
                            if (ckd) temp.remove(b.id) else temp.add(b.id)
                        })
                        Text(b.title)
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onSave(temp) }) { Text("Lưu") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Hủy") }
        }
    )
}
