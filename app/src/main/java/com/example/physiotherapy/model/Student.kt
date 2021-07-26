package com.example.physiotherapy.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Student(
    val name: String = "",
    val id: String = "",
    val phoneNumber : String = ""
) : Parcelable, Serializable
