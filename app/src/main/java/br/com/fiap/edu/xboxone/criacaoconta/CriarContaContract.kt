package br.com.fiap.edu.xboxone.criacaoconta

interface CriarContaContract {

    fun gravandoInformacao()

    fun usuarioGravadoComSucess()

    fun erroAoGravarUsuario(message: String)

}