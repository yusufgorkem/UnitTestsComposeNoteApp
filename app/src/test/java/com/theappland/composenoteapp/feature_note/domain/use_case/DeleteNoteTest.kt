package com.theappland.composenoteapp.feature_note.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.theappland.composenoteapp.feature_note.data.repository.FakeNoteRepository
import com.theappland.composenoteapp.feature_note.domain.model.Note
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class DeleteNoteTest {

    private lateinit var deleteNote: DeleteNote
    private lateinit var fakeRepository: FakeNoteRepository

    @Before
    fun setup() {
        fakeRepository = FakeNoteRepository()
        deleteNote = DeleteNote(fakeRepository)
    }

    @Test
    fun deleteNoteTest() {
        val note = Note("title", "content", 123, 1)
        var allNotes = listOf<Note>()

        runBlocking {
            fakeRepository.insertNote(note)
            fakeRepository.deleteNote(note)
            allNotes = fakeRepository.getNotes().first()
        }

        assertThat(allNotes).doesNotContain(note)
    }
}