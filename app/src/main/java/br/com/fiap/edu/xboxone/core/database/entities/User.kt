package br.com.fiap.edu.xboxone.core.database.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name= "password") val password: String?
)

/*
create table User(
    id INTEGER PRIMARY KEY,
    name TEXT,
    last_name TEXT
)
 */
