package com.zeller.terminalapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zeller.core_common.data_model.Transactions
import com.zeller.core_common.util.Constants.CTA_DEPOSIT
import com.zeller.core_common.util.Constants.CTA_WITHDRAW
import com.zeller.core_common.util.Constants.DEPOSIT_TITLE
import com.zeller.core_common.util.Constants.NOT_ENOUGH_BALANCE
import com.zeller.core_common.util.Constants.WITHDRAW_TITLE
import com.zeller.core_database.DatabaseClient
import com.zeller.transaction_history.viewModel.TransactionViewModel
import com.zeller.injector.di.KoinInjector
import com.zeller.terminalapp.R
import com.zeller.terminalapp.databinding.ActivityMainBinding
import com.zeller.terminalapp.viewModel.MainViewModel
import org.koin.java.KoinJavaComponent.inject


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseClient: DatabaseClient

    init {
        KoinInjector.initiateKoin(this)
    }

    private val viewModel: TransactionViewModel by inject(TransactionViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        databaseClient = DatabaseClient(this)
        binding.depositButton.setOnClickListener(this)
        binding.withdrawButton.setOnClickListener(this)
        setContentView(binding.root)
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
                    binding.balance.text = MainViewModel.depositAmount(depositAmount)
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