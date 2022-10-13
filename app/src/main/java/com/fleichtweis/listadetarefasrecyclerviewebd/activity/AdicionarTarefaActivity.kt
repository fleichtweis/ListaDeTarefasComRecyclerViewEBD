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

        //Apenas ao fazer Edição
        var tarefa: Tarefa? = null
        val bundle = intent.extras
        if (bundle != null){
            tarefa = bundle.getSerializable("tarefa") as Tarefa
            binding.editTarefa.setText(tarefa.descricao)
        }


        with(binding){
            btnSalvar.setOnClickListener {

                if (editTarefa.text.isNotEmpty()){

                    if (tarefa != null){
                        atualizarTarefa( tarefa )
                    }else{
                        salvarTarefa()
                    }



                }else{
                    Toast.makeText(applicationContext, "Preencha uma tarefa", Toast.LENGTH_SHORT).show()
                }

            }

        }

    }

    private fun atualizarTarefa(tarefa: Tarefa) {

        tarefa.descricao = binding.editTarefa.text.toString()

        val tarefaDAO = TarefaDAO(this)
        if (tarefaDAO.atualizar(tarefa)){
            Toast.makeText(this, "Tarefa atualizada com sucesso", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Erro ao atualizar tarefa", Toast.LENGTH_SHORT).show()
        }
        finish() //Fechar activity
    }

    private fun salvarTarefa() {

        val tarefa = Tarefa(
            -1,
            binding.editTarefa.text.toString(),
            "default"
        )

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