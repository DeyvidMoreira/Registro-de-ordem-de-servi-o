package com.example.tellcom.view.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.telecom.R
import com.example.telecom.databinding.RowOrderBinding
import com.example.tellcom.service.model.OrderModel

class OrderAdapter(private var orders: List<OrderModel>, private val listener: OrderItemListener) :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {


    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal val binding = RowOrderBinding.bind(itemView)

        fun bind(order: OrderModel, position: Int) {
            binding.tvProtocolNumer.text = order.protocolNumber
            binding.tvOrderName.text = order.clientName
            binding.tvDropValue.text = order.dropValue
            binding.tvDropMeasure.text = "M"

            // Definir a cor de fundo com base no status
            when (order.status) {
                1 -> binding.cvRowOrder.setBackgroundColor(Color.GREEN)
                2 -> binding.cvRowOrder.setBackgroundColor(Color.RED)
                else -> binding.cvRowOrder.setBackgroundColor(Color.WHITE)
            }

            //Configurar listener para cbDone
            binding.cbDone.setOnCheckedChangeListener(null)
            binding.cbDone.isChecked = order.status == 1
            binding.cbDone.setOnCheckedChangeListener { _, isChecked ->
                // Marcar como Feito (1) ou Andamento (3)
                order.status= if(isChecked) 1 else 3
                notifyDataSetChanged()
            }
            //Configurar listener para cdBroken
            binding.cbBroken.setOnCheckedChangeListener(null)
            binding.cbBroken.isChecked = order.status == 2
            binding.cbBroken.setOnCheckedChangeListener { _, isChecked ->
                order.status = if (isChecked) 2 else 3
                notifyDataSetChanged()
            }


            /*
                    binding.cbDone.setOnClickListener {
                        if (binding.cbDone.isChecked) {
                            listener.onDoneClicked(position, true)
                            binding.cvRowOrder.background
                        }
                    }

                    binding.cbBroken.setOnClickListener {
                        if (binding.cbBroken.isChecked) {
                            listener.onNotDoneClicked(position, false)
                        }
                    }

                    if (order.checked) {
                        binding.cvRowOrder.setBackgroundColor(Color.GREEN)
                    } else {
                        binding.cvRowOrder.setBackgroundColor(Color.WHITE)
                    }
                */

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(orders[position], position)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    fun setData(newList: List<OrderModel>) {
        orders = newList
        notifyDataSetChanged()
    }

    fun getOrderAtPosition(position: Int): OrderModel? {
        if (position in 0 until orders.size) {
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