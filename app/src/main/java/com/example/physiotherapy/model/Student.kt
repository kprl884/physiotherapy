package com.example.physiotherapy.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Student(
    val name: String,
    val surName: String,
    val userName: String?,
    val password: String?,
    val id: Int?
) : Parcelable, Serializable
