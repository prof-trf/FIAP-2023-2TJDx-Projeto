package br.com.fiap.edu.xboxone.core.network.entities.xbox

data class UserBody(
    val email: String,
    val password: String,
    val active: Boolean
)