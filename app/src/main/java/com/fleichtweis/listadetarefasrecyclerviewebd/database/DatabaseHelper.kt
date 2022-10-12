package com.fleichtweis.listadetarefasrecyclerviewebd.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context, "tarefas.db", null, 1
) {

    companion object{
        const val TABELA_TAREFAS = "tarefas"
        const val ID_TAREFA = "id_tarefa"
        const val DESCRICAO = "descricao"
        const val DATA_CRIACAO = "data_criacao"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val sqlCriacao = "CREATE TABLE if not EXISTS $TABELA_TAREFAS(" +
                "$ID_TAREFA INTEGER not NULL PRIMARY KEY AUTOINCREMENT," +
                "$DESCRICAO varchar(50)," +
                "$DATA_CRIACAO datetime NOT NULL DEFAULT CURRENT_TIMESTAMP" +
                ");"

        try {
            db?.execSQL(sqlCriacao)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}