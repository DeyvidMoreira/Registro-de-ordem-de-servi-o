package com.example.tellcom.view.activitys

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.telecom.databinding.ActivityOrderBinding
import com.example.tellcom.service.constants.ConstantsDialog
import com.example.tellcom.service.constants.ConstantsOrders
import com.example.tellcom.service.model.OrderModel
import com.example.tellcom.view.adapter.OrderAdapter
import com.example.tellcom.view.tools.AlertDialogHelper
import com.example.tellcom.viewModel.FormOrderViewModel

class OrderActivity : AppCompatActivity(), View.OnClickListener, OrderAdapter.OrderItemListener {
    private lateinit var binding: ActivityOrderBinding
    private lateinit var orderAdapter: OrderAdapter
    private val orderViewModel: FormOrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicialize a classe NOTIFICATION com o contexto desta atividade
        ConstantsDialog.DIALOG.context = this

        // RecyclerView Layout
        binding.rvOrders.layoutManager = LinearLayoutManager(this)

        // Inicia o adapter como uma lista vazia e passa a própria activity como listener
        orderAdapter = OrderAdapter(listOf(), this)
        binding.rvOrders.adapter = orderAdapter

        //Adiciona o IteTouchHelper à RecyclerView
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.rvOrders)



        observe()
        onListeners()
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.fabAddOrder.id -> {
                val intent = Intent(applicationContext, FormOrderActivity::class.java)
                startActivity(intent)
            }
        }
    }

    //Método para movimentação do swipe
    private val swipeHandler = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false // Não precisamos implementar movimentação
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val order = orderAdapter.getOrderAtPosition(position)
            order?.let {
                AlertDialogHelper.showDeleteConfirmationDialog(
                    this@OrderActivity,
                    orderViewModel,
                    orderAdapter,
                    it,
                    position
                )
            }
        }
    }


    // Método chamado quando a checkbox "Done" é clicada
    override fun onDoneClicked(position: Int, isChecked: Boolean) {
        val order = orderAdapter.getOrderAtPosition(position)
        order?.let {

            if (isChecked) {
                it.status = "done"
                orderAdapter.notifyItemChanged(position)
            } else {
                it.status = "progress"
            }
            orderViewModel.updateOrder(it)
        }

    }

    // Método chamado quando a checkbox "Not Done" é clicada
    override fun onNotDoneClicked(position: Int, isChecked: Boolean) {
        val order = orderAdapter.getOrderAtPosition(position)
        order?.let {
            if (isChecked) {
                it.status = "broken"
                orderAdapter.notifyItemChanged(position)
            } else {
                it.status ="progress"
            }
            orderViewModel.updateOrder(it)
        }
    }

    // Método para mostrar ou esconder o texto de lista vazia
    fun noneListText() {
        binding.tvNoOrder.visibility = if (orderAdapter.itemCount == 0) View.VISIBLE else View.GONE
        binding.rvOrders.visibility = if (orderAdapter.itemCount == 0) View.GONE else View.VISIBLE
    }

    // Método para observar as mudanças na lista de pedidos no ViewModel
    private fun observe() {
        // Observa as mudanças na lista de pedidos no ViewModel
        orderViewModel.allOrders.observe(this) { orders ->
            orders?.let {
                orderAdapter.setData(it)
                noneListText()
            }
        }
    }

    // Método para definir os listeners
    private fun onListeners() {
        binding.fabAddOrder.setOnClickListener(this)
    }

}