package com.fleichtweis.listadetarefasrecyclerviewebd.database

import android.content.ContentValues
import android.content.Context
import com.fleichtweis.listadetarefasrecyclerviewebd.model.Tarefa

class TarefaDAO(context: Context) : ITarefaDAO {

    private val leitura = DatabaseHelper(context).readableDatabase
    private val escrita = DatabaseHelper(context).writableDatabase


    override fun salvar(tarefa: Tarefa): Boolean {

        val conteudo = ContentValues()
        conteudo.put("${DatabaseHelper.DESCRICAO}", tarefa.descricao)

        try{
            escrita.insert(
                DatabaseHelper.TABELA_TAREFAS,
                null,
                conteudo
            )
        }catch (e: Exception){
            e.printStackTrace()
            return false
        }
        return true
    }

    override fun atualizar(tarefa: Tarefa): Boolean {

        val conteudo = ContentValues()
        conteudo.put("${DatabaseHelper.DESCRICAO}", tarefa.descricao)

        val args = arrayOf( tarefa.idTarefa.toString())

        try{
            escrita.update(
                DatabaseHelper.TABELA_TAREFAS,
                conteudo,
                "${DatabaseHelper.ID_TAREFA} = ? ",
                args
            )
        }catch (e: Exception){
            e.printStackTrace()
            return false
        }
        return true
    }

    override fun remover(idTarefa: Int): Boolean {

        val args = arrayOf(idTarefa.toString())
        try{
            escrita.delete(
                DatabaseHelper.TABELA_TAREFAS,
                "${DatabaseHelper.ID_TAREFA} = ?", args
            )
        }catch (e: Exception){
            e.printStackTrace()
            return false
        }
        return true
    }

    override fun listar(): List<Tarefa> {

        val listaTarefas = mutableListOf<Tarefa>()

        try{
            val sql = "SELECT " +
                    "id_tarefa, " +
                    "descricao, " +
                    "strftime('%d%m%Y %H:%M', data_criacao) data_criacao " +
                    "FROM ${DatabaseHelper.TABELA_TAREFAS}"

            val cursor = leitura.rawQuery(sql, null)

            val indiceId = cursor.getColumnIndex(DatabaseHelper.ID_TAREFA)
            val indiceDescricao = cursor.getColumnIndex(DatabaseHelper.DESCRICAO)
            val indiceData = cursor.getColumnIndex(DatabaseHelper.DATA_CRIACAO)

            while (cursor.moveToNext()){
                val idTarefa = cursor.getInt(indiceId)
                val descricao = cursor.getString(indiceDescricao)
                val data = cursor.getString(indiceData)

                listaTarefas.add(
                    Tarefa(idTarefa, descricao, data)
                )
            }
        }catch (e: Exception){
            e.printStackTrace()
            return listaTarefas
        }
        return listaTarefas
    }


}