package com.fleichtweis.listadetarefasrecyclerviewebd.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fleichtweis.listadetarefasrecyclerviewebd.R
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


            tarefaAdapter = TarefaAdapter()

            rvTarefas.adapter = tarefaAdapter
            rvTarefas.layoutManager = LinearLayoutManager(applicationContext)

        }
    }

    override fun onStart() {
        super.onStart()
        //Listar tarefas
        val tarefaDao = TarefaDAO(applicationContext)
        listaTarefas = tarefaDao.listar()
        tarefaAdapter?.adicionarLista(listaTarefas)



    }
}