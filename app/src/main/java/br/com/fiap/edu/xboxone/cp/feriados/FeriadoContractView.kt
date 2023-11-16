package br.com.fiap.edu.xboxone.cp.feriados

import br.com.fiap.edu.xboxone.core.network.entities.invertexto.Holiday
import java.util.ArrayList

interface FeriadoContractView {
    fun feriadoCarregadoComSucesso(holidays: ArrayList<Holiday>)
    fun falhaCarregamentoFeriado(message: String)
    fun localizandoListaFeriados()
}