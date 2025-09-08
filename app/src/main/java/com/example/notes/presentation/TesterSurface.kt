import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ToDoTask(
    val id: Int,
    val text: String,
    val icon: ImageVector,
    val iconColor: Color,
    val iconBackgroundColor: Color
)

data class TaskSection(
    val title: String,
    val tasks: List<ToDoTask>,
    val isHighlighted: Boolean = false
)

val dummyTasks = listOf(
    TaskSection(
        title = "HIGH (1)",
        tasks = listOf(
            ToDoTask(1, "Pay electricity bill", Icons.Rounded.Edit, Color(0xFFD9A507), Color(0xFFFFFBE6))
        )
    ),
    TaskSection(
        title = "MEDIUM (1)",
        tasks = listOf(
            ToDoTask(2, "Review monthly budget", Icons.Rounded.Edit, Color(0xFFE57373), Color(0xFFFFF5F5))
        ),
        isHighlighted = true
    ),
    TaskSection(
        title = "LOW",
        tasks = emptyList()
    ),
    TaskSection(
        title = "TO-DO (4)",
        tasks = listOf(
            ToDoTask(
                3,
                "Book doctor's appointment",
                Icons.Rounded.Home,
                Color(0xFF64B5F6),
                Color(0xFFE3F2FD)
            ),
            ToDoTask(4, "Update CV", Icons.Rounded.Edit, Color(0xFF81C784), Color(0xFFE8F5E9)),
            ToDoTask(5, "Brainstorm ideas for team meeting", Icons.Rounded.FavoriteBorder, Color(0xFFBA68C8), Color(0xFFF3E5F5)),
            ToDoTask(6, "Call mom", Icons.Rounded.Call, Color(0xFF4DB6AC), Color(0xFFE0F2F1))
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoScreen() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val navItems = listOf("To-do", "Today", "Focus")

    val userTasks = remember { mutableStateListOf<ToDoTask>() }
    var nextTaskId by remember { mutableIntStateOf(7) }

    Scaffold(
        containerColor = Color(0xFFF9F9F9),
        topBar = {
            TopAppBar(
                title = {
                    Text("To-Do's", fontWeight = FontWeight.Bold, fontSize = 24.sp)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        bottomBar = {
            Column(modifier = Modifier.background(MaterialTheme.colorScheme.surface)) {
                ToDoInputBar(
                    onAdd = { text ->
                        val trimmed = text.trim()
                        if (trimmed.isNotEmpty()) {
                            userTasks.add(
                                ToDoTask(
                                    id = nextTaskId,
                                    text = trimmed,
                                    icon = Icons.Rounded.Edit,
                                    iconColor = Color(0xFF81C784),
                                    iconBackgroundColor = Color(0xFFE8F5E9)
                                )
                            )
                            nextTaskId += 1
                        }
                    }
                )
                NavigationBar {
                    navItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = when (index) {
                                        0 -> Icons.Default.CheckCircle
                                        1 -> Icons.Default.DateRange
                                        else -> Icons.Default.CheckCircle // Placeholder
                                    },
                                    contentDescription = item
                                )
                            },
                            label = { Text(item) },
                            selected = selectedItem == index,
                            onClick = { selectedItem = index }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        ToDoList(
            sections = dummyTasks.map { section ->
                if (section.title.startsWith("TO-DO")) section.copy(tasks = section.tasks + userTasks) else section
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ToDoList(sections: List<TaskSection>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        sections.forEach { section ->
            item {
                TaskSectionHeader(
                    title = section.title,
                    showSwipeToAdd = section.title == "LOW"
                )
            }
            items(section.tasks) { task ->
                ToDoTaskItem(task = task, isHighlighted = section.isHighlighted)
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun TaskSectionHeader(title: String, showSwipeToAdd: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            fontWeight = FontWeight.Bold
        )
        if (showSwipeToAdd) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "SWIPE TO ADD",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = Color.LightGray,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}

@Composable
fun ToDoTaskItem(task: ToDoTask, isHighlighted: Boolean) {
    val backgroundColor = if (isHighlighted) Color(0xFFFFF0E1) else MaterialTheme.colorScheme.surface

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        color = backgroundColor,
        border = if (isHighlighted) BorderStroke(1.dp, Color(0xFFE57373).copy(alpha = 0.5f)) else null
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(task.iconBackgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = task.icon,
                    contentDescription = null,
                    tint = task.iconColor,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = task.text,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                imageVector = Icons.Outlined.DateRange,
                contentDescription = "Mark as complete",
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun ToDoInputBar(onAdd: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.weight(1f),
            placeholder = { Text("Type to begin") },
            singleLine = true,
            shape = CircleShape
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ToDoScreenPreview() {
    MaterialTheme {
        ToDoScreen()
    }
}