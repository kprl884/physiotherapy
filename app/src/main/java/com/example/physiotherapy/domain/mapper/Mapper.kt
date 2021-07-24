package com.example.physiotherapy.domain.mapper

import com.example.physiotherapy.model.Student

object Mapper {

    fun mapStudent(studentHaspMapParam: HashMap<String, *>): MutableList<Student> {
        var studentHashMap: HashMap<String, *>? = null
        val studentArray: MutableList<Student> = arrayListOf()

        studentHaspMapParam.forEach { it ->
            studentHashMap = studentHaspMapParam[it.key] as HashMap<String, *>
            studentArray.add(
                Student(
                    try {
                        studentHashMap!!["name"] as String
                    } catch (e: java.lang.Exception) {
                        e.localizedMessage
                    },
                    try {
                        studentHashMap!!["id"] as String
                    } catch (e: java.lang.Exception) {
                        e.localizedMessage
                    },
                    try {
                        studentHashMap!!["phoneNumber"] as String
                    } catch (e: java.lang.Exception) {
                        e.localizedMessage
                    },
                )
            )

        }
        return studentArray
    }
}