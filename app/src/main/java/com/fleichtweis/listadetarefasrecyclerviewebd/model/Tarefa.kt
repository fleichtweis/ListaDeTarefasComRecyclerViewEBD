package com.fleichtweis.listadetarefasrecyclerviewebd.model

import java.io.Serializable

data class Tarefa(
    val idTarefa: Int,
    var descricao: String,
    val dataCriacao: String
): Serializable
