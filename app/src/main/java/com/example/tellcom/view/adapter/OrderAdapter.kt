package com.example.tellcom.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.telecom.R
import com.example.telecom.databinding.RowOrderBinding
import com.example.tellcom.service.model.OrderModel
import com.example.tellcom.view.OrderActivity

class OrderAdapter(private var orders: List<OrderModel>, private val listener: OrderActivity) :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {


    // ViewHolder para OrderModel
    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = RowOrderBinding.bind(itemView)

        // Método para ligar os dados de OrderModel aos elementos de UI
        @SuppressLint("NotifyDataSetChanged")
        fun bindOrder(order: OrderModel, position: Int) {
            binding.tvProtocolNumer.text = order.protocolNumber
            binding.tvOrderName.text = order.clientName
            binding.tvDropValue.text = order.dropValue
            binding.tvDropMeasure.text = "M"

            // Definir a cor de fundo com base no status
            when (order.status) {
                1 -> binding.cvRowOrder.background =
                    ContextCompat.getDrawable(itemView.context, R.color.green)

                2 -> binding.cvRowOrder.background =
                    ContextCompat.getDrawable(itemView.context, R.color.red)

                else -> binding.cvRowOrder.background =
                    ContextCompat.getDrawable(itemView.context, R.color.white)
            }

            //Configurar listener para cbDone
            binding.cbDone.setOnCheckedChangeListener(null)
            binding.cbDone.isChecked = order.status == 1
            binding.cbDone.setOnCheckedChangeListener { _, isChecked ->
                listener.onDoneClicked(position, isChecked)
                notifyDataSetChanged()
            }

            //Configurar listener para cbBroken
            binding.cbBroken.setOnCheckedChangeListener(null)
            binding.cbBroken.isChecked = order.status == 2
            binding.cbBroken.setOnCheckedChangeListener { _, isChecked ->
                listener.onNotDoneClicked(position, isChecked)
                notifyDataSetChanged()
            }

        }

    }

    // Método necessário para criar o ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_order, parent, false)
        return OrderViewHolder(view)
    }

    // Método necessário para ligar os dados a um ViewHolder
    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bindOrder(orders[position], position)
    }

    // Método para retornar o número de itens na lista
    override fun getItemCount(): Int {
        return orders.size
    }

    // Método para atualizar a lista de pedidos
    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<OrderModel>) {
        orders = newList
        notifyDataSetChanged()
    }

    // Método para obter um pedido em uma determinada posição
    fun getOrderAtPosition(position: Int): OrderModel? {
        if (position in orders.indices) {
            return orders[position]
        }
        return null
    }

    // Interface que a Activity irá implementar
    interface OrderItemListener {
        fun onDoneClicked(position: Int, isChecked: Boolean)
        fun onNotDoneClicked(position: Int, isChecked: Boolean)

    }

}