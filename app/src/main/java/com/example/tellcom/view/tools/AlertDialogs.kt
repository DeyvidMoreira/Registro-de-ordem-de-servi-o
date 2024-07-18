package com.example.tellcom.view.tools

import android.app.AlertDialog
import android.content.Context
import com.example.tellcom.service.constants.ConstantsDialog
import com.example.tellcom.service.model.OrderModel
import com.example.tellcom.view.adapter.OrderAdapter
import com.example.tellcom.viewModel.FormOrderViewModel


class AlertDialogHelper {
    companion object {
        fun showDeleteConfirmationDialog(
            context: Context,
            orderViewModel: FormOrderViewModel,
            orderAdapter: OrderAdapter,
            order: OrderModel,
            position: Int
        ) {
            AlertDialog.Builder(context)
                .setTitle(ConstantsDialog.DIALOG.ALERT_DIALOG_TITLE)
                .setMessage(ConstantsDialog.DIALOG.ALERT_DIALOG_MESSAGE)
                .setPositiveButton(ConstantsDialog.DIALOG.ALERT_DIALOG_POSITIVE) { _, _ ->
                    orderViewModel.deleteOrder(order)
                    orderAdapter.notifyItemRemoved(position)
                }
                .setNegativeButton(ConstantsDialog.DIALOG.ALERT_DIALOG_NEGATIVE) { dialog, _ ->
                    orderAdapter.notifyItemChanged(position)
                    dialog.dismiss()
                }
                .create()
                .show()
        }
    }
}