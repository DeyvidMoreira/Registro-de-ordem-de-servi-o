package com.example.tellcom.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.telecom.R
import com.example.telecom.databinding.RowOrderBinding
import com.example.tellcom.service.model.OrderModel
import com.example.tellcom.view.activitys.OrderActivity
import com.example.tellcom.view.tools.OrderDiffCallback

class OrderAdapter(private var orders: List<OrderModel>, private val listener: OrderActivity) :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {


    // ViewHolder para OrderModel
    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = RowOrderBinding.bind(itemView)

        // Método para ligar os dados de OrderModel aos elementos de UI
        fun bindOrder(order: OrderModel, position: Int) {
            binding.tvProtocolNumer.text = order.protocolNumber
            binding.tvOrderName.text = order.clientName
            binding.tvDropValue.text = order.dropValue
            binding.tvDropMeasure.text = itemView.context.getString(R.string.Metros)

            // Definir a cor de fundo com base no status
            val backgroundColor = when (order.status) {
                "done" -> R.color.green
                "broken" -> R.color.red
                else ->R.color.white
            }
            binding.cvRowOrder.setBackgroundResource(backgroundColor)

            //Configurar listener para cbDone
            binding.cbDone.setOnCheckedChangeListener(null)
            binding.cbDone.isChecked = order.status == "done"
            binding.cbDone.setOnCheckedChangeListener { _, isChecked ->
                listener.onDoneClicked(position, isChecked)
                notifyItemChanged(position)
            }

            //Configurar listener para cbBroken
            binding.cbBroken.setOnCheckedChangeListener(null)
            binding.cbBroken.isChecked = order.status == "broken"
            binding.cbBroken.setOnCheckedChangeListener { _, isChecked ->
                listener.onNotDoneClicked(position, isChecked)
                notifyItemChanged(position)
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
    fun setData(newList: List<OrderModel>) {
        val diffCallback = OrderDiffCallback(orders, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        orders = newList
        diffResult.dispatchUpdatesTo(this)
    }

    // Método para obter um pedido em uma determinada posição
    fun getOrderAtPosition(position: Int): OrderModel? {
        return if (position in orders.indices) return orders[position] else  null
    }

    // Interface que a Activity irá implementar
    interface OrderItemListener {
        fun onDoneClicked(position: Int, isChecked: Boolean)
        fun onNotDoneClicked(position: Int, isChecked: Boolean)

    }

}