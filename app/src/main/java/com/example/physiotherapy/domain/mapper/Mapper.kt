package com.example.physiotherapy.domain.mapper

import com.example.physiotherapy.model.Note
import com.example.physiotherapy.model.Tag

object Mapper {

    fun mapNote(noteHaspMapParam: HashMap<String, *>): Note {
        val noteHashMap: HashMap<String, *>? = noteHaspMapParam

        return Note(
            try {
                noteHashMap!!["description"] as String
            } catch (e: java.lang.Exception) {
                e.localizedMessage
            }
        )
    }
}