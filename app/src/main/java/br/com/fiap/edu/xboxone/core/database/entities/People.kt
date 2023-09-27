package br.com.fiap.edu.xboxone.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class People(
    @PrimaryKey val id: Int
)