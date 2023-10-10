package br.com.fiap.edu.xboxone.crieuma

interface CrieUmaContract {

    fun loadingCadastrandoUsuario()
    fun usuarioCadastradoComSucesso()

    fun usuarioFalhaNoCadastro(message: String)

}