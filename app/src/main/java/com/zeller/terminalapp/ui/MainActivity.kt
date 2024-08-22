package com.zeller.terminalapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.zeller.core_common.data_model.Transactions
import com.zeller.core_common.util.Constants.CTA_DEPOSIT
import com.zeller.core_common.util.Constants.CTA_WITHDRAW
import com.zeller.core_common.util.Constants.DEPOSIT_TITLE
import com.zeller.core_common.util.Constants.INVALID_AMOUNT
import com.zeller.core_common.util.Constants.NOT_ENOUGH_BALANCE
import com.zeller.core_common.util.Constants.WITHDRAW_TITLE
import com.zeller.terminalapp.R
import com.zeller.terminalapp.databinding.ActivityMainBinding
import com.zeller.transaction_history.viewModel.TransactionViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: TransactionViewModel by inject(TransactionViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAvailableBalance()
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.depositButton.setOnClickListener(this)
        binding.withdrawButton.setOnClickListener(this)
        setContentView(binding.root)
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.availableBalance.asStateFlow().collect { binding.balance.text = it }
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.withdrawButton -> {
                showTransactionAlertDialog(
                    this,
                    WITHDRAW_TITLE,
                    CTA_WITHDRAW
                ) { withDrawAmount ->
                    if (withDrawAmount.isNullOrBlank() || withDrawAmount.toBigDecimal() <= 0.toBigDecimal()) {
                        Toast.makeText(this, INVALID_AMOUNT, Toast.LENGTH_LONG).show()
                        return@showTransactionAlertDialog
                    }
                    if (viewModel.isAmountValidForWithDraw(withDrawAmount?.toBigDecimal())) {
                        viewModel.insertTransaction(
                            Transactions(
                                isDeposit = false,
                                amount = withDrawAmount?.toBigDecimal() ?: 0.toBigDecimal(),
                                timeStamp = System.currentTimeMillis()
                            )
                        )
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
                    if (depositAmount.isNullOrBlank() || depositAmount.toBigDecimal() <= 0.toBigDecimal()) {
                        Toast.makeText(this, INVALID_AMOUNT, Toast.LENGTH_LONG).show()
                        return@showTransactionAlertDialog
                    }
                    viewModel.insertTransaction(
                        Transactions(
                            isDeposit = true,
                            amount = depositAmount?.toBigDecimal() ?: 0.toBigDecimal(),
                            timeStamp = System.currentTimeMillis()
                        )
                    )

                }
            }
        }
    }
}