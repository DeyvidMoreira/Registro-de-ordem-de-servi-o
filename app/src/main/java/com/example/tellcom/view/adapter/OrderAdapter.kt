package com.example.tellcom.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

            binding.ivDone.setOnClickListener {
                listener.onDoneClicked(position, true)
            }
            binding.ivNotDone.setOnClickListener {
                listener.onNotDoneClicked(position, false)
            }

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
        fun onDoneClicked(position: Int, isDoneClicked: Boolean)
        fun onNotDoneClicked(position: Int, isDoneClicked: Boolean)
    }
}