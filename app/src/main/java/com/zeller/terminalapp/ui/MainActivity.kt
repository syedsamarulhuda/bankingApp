package com.zeller.terminalapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zeller.terminalapp.R
import com.zeller.terminalapp.databinding.ActivityMainBinding
import com.zeller.terminalapp.util.Constants.CTA_DEPOSIT
import com.zeller.terminalapp.util.Constants.CTA_WITHDRAW
import com.zeller.terminalapp.util.Constants.DEPOSIT_TITLE
import com.zeller.terminalapp.util.Constants.NOT_ENOUGH_BALANCE
import com.zeller.terminalapp.util.Constants.WITHDRAW_TITLE
import com.zeller.terminalapp.viewModel.MainViewModel


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.depositButton.setOnClickListener(this)
        binding.withdrawButton.setOnClickListener(this)
        setContentView(binding.root)
        binding.transactionHistoryView.updateTransactionHistory(MainViewModel.transactions.getTransactions())
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.withdrawButton -> {
                showTransactionAlertDialog(
                    this,
                    WITHDRAW_TITLE,
                    CTA_WITHDRAW
                ) { withDrawAmount ->
                    if (MainViewModel.isAmountValidForWithDraw(withDrawAmount?.toBigDecimal())) {
                        binding.balance.text = MainViewModel.withdrawAmount(withDrawAmount)
                    } else {
                        Toast.makeText(this, NOT_ENOUGH_BALANCE, Toast.LENGTH_LONG).show()
                    }
                }
            }

            R.id.depositButton -> {
                showTransactionAlertDialog(
                    this,
                    DEPOSIT_TITLE,
                    CTA_DEPOSIT
                ) { depositAmount ->
                    binding.balance.text = MainViewModel.depositAmount(depositAmount)
                }
            }
        }
    }
}