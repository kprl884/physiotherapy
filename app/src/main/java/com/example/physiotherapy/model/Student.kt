package com.example.physiotherapy.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Student(
    val id: Int?,
    val name: String,
    val surName: String,
) : Parcelable, Serializable
