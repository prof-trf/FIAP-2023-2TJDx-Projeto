package br.com.fiap.edu.xboxone.core.network.entities.xbox

data class RegisterUserBody(
    val email: String,
    val password: String,
    val active: Boolean
)
