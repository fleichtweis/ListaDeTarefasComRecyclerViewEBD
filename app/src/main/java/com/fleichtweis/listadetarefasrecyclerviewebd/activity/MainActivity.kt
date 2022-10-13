package com.fleichtweis.listadetarefasrecyclerviewebd.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.fleichtweis.listadetarefasrecyclerviewebd.adapter.TarefaAdapter
import com.fleichtweis.listadetarefasrecyclerviewebd.database.TarefaDAO
import com.fleichtweis.listadetarefasrecyclerviewebd.databinding.ActivityMainBinding
import com.fleichtweis.listadetarefasrecyclerviewebd.model.Tarefa

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var listaTarefas: List<Tarefa> = listOf()
    private var tarefaAdapter: TarefaAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        with(binding){
            fabAdicionar.setOnClickListener {
                val intent = Intent(applicationContext, AdicionarTarefaActivity::class.java)
                startActivity(intent)
            }


            tarefaAdapter = TarefaAdapter(
                { id -> confirmarExcluir(id) },
                { tarefa -> editar(tarefa)}

            )

            rvTarefas.adapter = tarefaAdapter
            rvTarefas.layoutManager = LinearLayoutManager(applicationContext)

        }
    }



    override fun onStart() {
        super.onStart()
        atualizarListaTarefas()
    }

    private fun editar(tarefa: Tarefa) {

        val intent = Intent(this, AdicionarTarefaActivity::class.java)
        intent.putExtra("tarefa", tarefa)
        startActivity(intent)
    }

    private fun confirmarExcluir(id: Int){

        if (id == null) return

        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle("Confirmar Exclusão")
        alertBuilder.setMessage("Deseja realmente excluir a tarefa?")
        alertBuilder.setPositiveButton("Sim"){ dialog, _ ->
            val tarefaDAO = TarefaDAO(applicationContext)
            if (tarefaDAO.remover(id)){
                atualizarListaTarefas()
            }
            dialog.dismiss()
        }
        alertBuilder.setNegativeButton("Não"){ dialog, _ ->
            dialog.dismiss()
        }
        alertBuilder.create().show()



    }
    private fun atualizarListaTarefas(){
        //Listar tarefas
        val tarefaDao = TarefaDAO(applicationContext)
        listaTarefas = tarefaDao.listar()
        tarefaAdapter?.adicionarLista(listaTarefas)


    }

}