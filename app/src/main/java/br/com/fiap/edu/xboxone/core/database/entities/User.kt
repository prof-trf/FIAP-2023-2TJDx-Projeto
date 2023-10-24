package br.com.fiap.edu.xboxone.core.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class User(
    @PrimaryKey val id: Int,
    @ColumnInfo(name= "active") val active: Int,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name= "password") val password: String,
    @ColumnInfo(name= "type") val type: String?,
): Serializable

/*
create table User(
    id INTEGER PRIMARY KEY,
    name TEXT,
    last_name TEXT
)
 */
