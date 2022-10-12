package com.fleichtweis.listadetarefasrecyclerviewebd.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fleichtweis.listadetarefasrecyclerviewebd.R
import com.fleichtweis.listadetarefasrecyclerviewebd.database.TarefaDAO
import com.fleichtweis.listadetarefasrecyclerviewebd.databinding.ActivityAdicionarTarefaBinding
import com.fleichtweis.listadetarefasrecyclerviewebd.model.Tarefa

class AdicionarTarefaActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityAdicionarTarefaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            btnSalvar.setOnClickListener {
                if (editTarefa.text.isNotEmpty()){
                    val tarefa = Tarefa(
                        -1,
                        editTarefa.text.toString(),
                        "default"
                    )
                    salvarTarefa(tarefa)
                }else{
                    Toast.makeText(applicationContext, "Preencha uma tarefa", Toast.LENGTH_SHORT).show()
                }

            }

        }

    }

    private fun salvarTarefa(tarefa: Tarefa) {

        val tarefaDAO = TarefaDAO(this)

        if (tarefaDAO.salvar(tarefa)){
            Toast.makeText(this, "Tarefa salva com sucesso", Toast.LENGTH_SHORT).show()
            finish() //Fechar activity
        }else{
            Toast.makeText(this, "Erro ao salvar tarefa", Toast.LENGTH_SHORT).show()
            finish()
        }

    }
}