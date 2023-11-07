package br.com.fiap.edu.xboxone.core.network.entities.xbox

data class VerifyResponse(
    val email: String,
    val token: String,
)