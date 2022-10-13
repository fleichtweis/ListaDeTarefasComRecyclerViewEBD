package com.fleichtweis.listadetarefasrecyclerviewebd.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fleichtweis.listadetarefasrecyclerviewebd.databinding.ItemTarefaBinding
import com.fleichtweis.listadetarefasrecyclerviewebd.model.Tarefa

class TarefaAdapter(
    val onClickExcluir: (Int) -> Unit,
    val onClickEditar: (Tarefa) -> Unit
) : RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>() {

    private var listaTarefas = emptyList<Tarefa>()

    fun adicionarLista(listaTarefas: List<Tarefa>){
        this.listaTarefas = listaTarefas
        notifyDataSetChanged()
    }

    inner class TarefaViewHolder(itemTarefaBinding: ItemTarefaBinding) : RecyclerView.ViewHolder(itemTarefaBinding.root){

        private lateinit var binding: ItemTarefaBinding

        init {
            this.binding = itemTarefaBinding
        }

        fun bind(tarefa: Tarefa){
            binding.textDescricao.text = tarefa.descricao
            binding.textData.text = tarefa.dataCriacao

            binding.btnExcluir.setOnClickListener {
                onClickExcluir(tarefa.idTarefa)
            }

            binding.btnEditar.setOnClickListener {
                onClickEditar(tarefa)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val itemTarefaBinding = ItemTarefaBinding.inflate(layoutInflater, parent, false)

        return TarefaViewHolder(itemTarefaBinding)
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        val tarefa = listaTarefas[position]

        holder.bind(tarefa)
    }

    override fun getItemCount(): Int {
        return listaTarefas.size
    }
}