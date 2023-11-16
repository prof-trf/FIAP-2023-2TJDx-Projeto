package br.com.fiap.edu.xboxone.cp.numextenso

import br.com.fiap.edu.xboxone.core.network.entities.invertexto.Holiday
import br.com.fiap.edu.xboxone.core.network.entities.invertexto.NumberToWords
import java.util.ArrayList

interface NumExtensoContractView {
    fun numExtensoCarregadoComSucesso(numero: NumberToWords)
    fun falhaCarregamentoNumExtenso(message: String)
    fun gerandoNumExtenso()
}