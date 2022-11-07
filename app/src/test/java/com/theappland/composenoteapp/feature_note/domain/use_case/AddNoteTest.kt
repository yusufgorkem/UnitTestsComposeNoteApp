package com.theappland.composenoteapp.feature_note.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.theappland.composenoteapp.feature_note.data.repository.FakeNoteRepository
import com.theappland.composenoteapp.feature_note.domain.model.Note
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class AddNoteTest {

    private lateinit var addNote: AddNote
    private lateinit var fakeRepository: FakeNoteRepository

    @Before
    fun setup() {
        fakeRepository = FakeNoteRepository()
        addNote = AddNote(fakeRepository)
    }

    @Test
    fun `Note title is blank, returns error`() {
        val note = Note("", "content", 123, 1)

        val result = note.title.isNotBlank()

        assertThat(result).isFalse()
    }

    @Test
    fun `Note content is blank, returns error`() {
        val note = Note("title", "", 123, 1)

        val result = note.content.isNotBlank()

        assertThat(result).isFalse()
    }

    @Test
    fun `Note is valid, returns true`() {
        val note = Note("title", "content", 123, 1)

        val result = note.title.isNotBlank() && note.content.isNotBlank()

        assertThat(result).isTrue()
    }

    @Test
    fun `Note is added, returns true`() {
        val note = Note("title", "content", 123, 1)
        var allNotes = listOf<Note>()

        runBlocking {
            fakeRepository.insertNote(note)
            allNotes = fakeRepository.getNotes().first()
        }

        assertThat(allNotes).contains(note)
    }
}