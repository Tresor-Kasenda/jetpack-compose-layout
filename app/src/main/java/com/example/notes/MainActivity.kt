package com.example.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notes.ui.theme.NotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel: NotesViewModel = viewModel()
                    NotesList(
                        notes = viewModel.notes,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun NotesList(notes: List<Note>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.padding(10.dp)) {
        item() {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Notes List")
                IconButton(
                    onClick = {},
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.primary,
                    )
                ) {
                    Icon(Icons.Default.ArrowForward, contentDescription = "")
                }
            }
        }

        item {
            Divider(modifier = Modifier.padding(vertical = 10.dp), thickness = 1.dp)
        }

        items(notes, contentType = { it }) { note ->
            Text(
                text = "${note.title}: ${note.content}",
                modifier = Modifier.padding(vertical = 5.dp)
            )
        }
    }
}

@Composable
fun GridContent() {
//    LazyVerticalGrid(
//        columns = GridCells.Adaptive(128.dp),
//        horizontalArrangement = Arrangement.spacedBy(24.dp),
//    ) {
//        items(100) { index ->
//            Text(
//                text = "Item $index",
//                modifier = Modifier.padding(8.dp)
//            )
//        }
//    }

    LazyVerticalGrid(
        columns = object : GridCells {
            override fun Density.calculateCrossAxisCellSizes(
                availableSize: Int,
                spacing: Int
            ): List<Int> {
                val firstColumn = (availableSize - spacing) * 2 / 3
                val secondColumn = availableSize - spacing - firstColumn
                return listOf(firstColumn, secondColumn)
            }
        },
        horizontalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        items(100) { index ->
            Text(
                text = "Item $index",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesListPreview() {
    NotesTheme {
        NotesList(
            notes = listOf(
                Note(1, "First Note", "This is your first note!"),
                Note(2, "Second Note", "Another note example.")
            ),
        )
    }
}