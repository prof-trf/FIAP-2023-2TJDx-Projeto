package br.com.fiap.edu.xboxone.core.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String
)