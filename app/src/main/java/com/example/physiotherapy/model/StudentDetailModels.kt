package com.example.physiotherapy.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import javax.inject.Inject

@Parcelize
data class Task  constructor(
     val title: String,
     val todos: MutableList<Todo> = mutableListOf(),
     val tag: Tag? = null
) : Parcelable, Serializable

@Parcelize
data class Todo(
     val description: String,
     var isComplete: Boolean = false,
): Parcelable, Serializable

@Parcelize
data class Note(
     val description: String,
     val tag: Tag? = null
) : Parcelable, Serializable

@Parcelize
data class Tag(
     val name: String,
     val colourResId: Int,
): Parcelable, Serializable

data class SelectedStudentSlideModel(
     val title: String,
     val imageId : Int
)