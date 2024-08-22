package com.zeller.terminalapp.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zeller.core_common.util.Constants.CR
import com.zeller.core_common.util.Constants.DR
import com.zeller.core_common.data_model.Transactions
import com.zeller.core_common.util.getAmountWithCurrency
import com.zeller.core_common.util.getDateTime
import com.zeller.terminalapp.R

const val EMPTY_VIEW_TYPE = 0
const val DATA_VIEW_TYPE = 1
class TransactionHistoryAdapter(private val transactionsList: MutableList<Transactions>) :
    RecyclerView.Adapter<TransactionHistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.transaction_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (transactionsList.size == 0) {
            holder.transactionsView.visibility = View.GONE
            holder.noTransactionsView.visibility = View.VISIBLE
            return
        }
        val itemsViewModel = transactionsList[position]
        holder.tvDate.text = getDateTime(itemsViewModel.timeStamp)
        holder.tvAmount.text = getAmountWithCurrency(itemsViewModel.amount.toString())
        holder.tvType.text = if (itemsViewModel.isDeposit) CR else DR
        holder.tvType.setTextColor(if (itemsViewModel.isDeposit) Color.GREEN else Color.RED)
    }

    override fun getItemCount(): Int {
        return if (transactionsList.size == 0) {
            1
        } else {
            transactionsList.size
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDate: TextView = this.itemView.findViewById(R.id.date)
        val tvAmount: TextView = this.itemView.findViewById(R.id.amount)
        val tvType: TextView = this.itemView.findViewById(R.id.transaction_type)
        val transactionsView: View = this.itemView.findViewById(R.id.transactions_view)
        val noTransactionsView: View = this.itemView.findViewById(R.id.no_transaction_item_layout)
    }

    override fun getItemViewType(position: Int): Int {
        return if (transactionsList.size == 0)
            EMPTY_VIEW_TYPE
        else
            DATA_VIEW_TYPE
    }
}