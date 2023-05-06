package com.example.timeisearth.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var title: String?,
    var content: String?,
    var deadline: String?,
)