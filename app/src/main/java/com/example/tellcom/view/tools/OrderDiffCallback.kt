package com.example.tellcom.view.tools

import androidx.recyclerview.widget.DiffUtil
import com.example.tellcom.service.model.OrderModel

class OrderDiffCallback(
    private val oldList: List<OrderModel>,
    private val newList: List<OrderModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}