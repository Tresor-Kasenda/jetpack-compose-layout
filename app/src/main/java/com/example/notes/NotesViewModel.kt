package com.example.notes

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList

class NotesViewModel : ViewModel() {
    private val _notes = mutableStateListOf<Note>()
    val notes: List<Note> get() = _notes

    init {
        _notes.addAll(
            listOf(
                Note(1, "First Note", "This is your first note!"),
                Note(2, "Second Note", "Another note example.")
            )
        )
    }

    fun addNote(title: String, content: String) {
        val newId = if (_notes.isEmpty()) 1 else _notes.maxOf { it.id } + 1
        _notes.add(Note(newId, title, content))
    }
}
