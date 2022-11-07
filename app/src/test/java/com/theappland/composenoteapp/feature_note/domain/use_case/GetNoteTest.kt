package com.theappland.composenoteapp.feature_note.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.theappland.composenoteapp.feature_note.data.repository.FakeNoteRepository
import com.theappland.composenoteapp.feature_note.domain.model.Note
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetNoteTest {

    private lateinit var getNote: GetNote
    private lateinit var fakeRepository: FakeNoteRepository

    @Before
    fun setup() {
        fakeRepository = FakeNoteRepository()
        getNote = GetNote(fakeRepository)
    }

    @Test
    fun getNoteByIdTest() {
        val note = Note("title", "content", 123, 1, 5)
        var addedNote: Note? = null

        runBlocking {
            fakeRepository.insertNote(note)
            addedNote = fakeRepository.getNoteById(note.id!!)
        }

        assertThat(note.id).isEqualTo(addedNote!!.id)
    }
}