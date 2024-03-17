package com.example.tellcom.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.telecom.databinding.ActivityOrderBinding
import com.example.tellcom.view.adapter.OrderAdapter
import com.example.tellcom.viewModel.FormOrderViewModel
class OrderActivity : AppCompatActivity(), View.OnClickListener, OrderAdapter.OrderItemListener {
    private lateinit var binding: ActivityOrderBinding
    private lateinit var orderAdapter: OrderAdapter
    private val orderViewModel: FormOrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // RecyclerView Layout
        binding.rvOrders.layoutManager = LinearLayoutManager(this)

        // Inicia o adapter como uma lista vazia e passa a própria activity como listener
        orderAdapter = OrderAdapter(listOf(), this)
        binding.rvOrders.adapter = orderAdapter

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

    override fun onDoneClicked(position: Int, isDoneClicked: Boolean) {
        val order = orderAdapter.getOrderAtPosition(position)
        order?.let {
            //TODO IMPLEMENTAR A LOGICA DE ORDEM FEITA...
        }
        val message = if (isDoneClicked) "Done clicked" else "Not Done clicked"
        Toast.makeText(this, "$message at position $position", Toast.LENGTH_SHORT).show()

    }

    override fun onNotDoneClicked(position: Int, isDoneClicked: Boolean) {
        val order = orderAdapter.getOrderAtPosition(position)
        order?.let {
            //TODO IMPLEMENTAR A LOGICA DE ORDEM NÃO REALIZADA...
        }
        val message = if (isDoneClicked) "Done clicked" else "Not Done clicked"
        Toast.makeText(this, "$message at position $position", Toast.LENGTH_SHORT).show()

    }

    private fun noneListText() {
        binding.tvNoOrder.visibility = if (orderAdapter.itemCount == 0) View.VISIBLE else View.GONE
        binding.rvOrders.visibility = if (orderAdapter.itemCount == 0) View.GONE else View.VISIBLE
    }

    private fun observe() {
        // Observa as mudanças na lista de pedidos no ViewModel
        orderViewModel.allOrders.observe(this, { orders ->
            orders?.let {
                orderAdapter.setData(it)
                noneListText()
            }
        })
    }

    private fun onListeners() {
        binding.fabAddOrder.setOnClickListener(this)
    }

}
