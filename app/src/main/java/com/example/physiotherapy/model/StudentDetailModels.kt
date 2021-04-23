package com.example.physiotherapy.model

data class Task @JvmOverloads constructor(
     val title: String,
     val todos: MutableList<Todo> = mutableListOf(),
     val tag: Tag? = null
)

data class Todo(
     val description: String,
     val isComplete: Boolean,
)

data class Note(
     val description: String,
     val tag: Tag?
)

data class Tag(
     val name: String,
     val colourResId: Int,
)

data class SelectedStudentSlideModel(
     val title: String,
     val imageId : Int
)