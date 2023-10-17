package br.com.fiap.edu.xboxone.core.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val nome: String?,
    @ColumnInfo(name= "last_name") val sobrenome: String?,
    @ColumnInfo(name= "type") val type: String,
    @ColumnInfo(name= "active") val active: Int
)

/*
create table User(
    id INTEGER PRIMARY KEY,
    name TEXT,
    last_name TEXT
)
 */
