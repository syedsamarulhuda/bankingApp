package com.bankingApp.transaction_history

import com.bankingApp.core_common.data_model.Transactions
import com.bankingApp.core_common.util.getAmountWithCurrency
import com.bankingApp.transaction_history.usecase.TransactionHistoryUseCase
import com.bankingApp.transaction_history.usecase.TransactionUseCase
import com.bankingApp.transaction_history.viewModel.TransactionViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class TransactionViewModelTest {

    @Mock
    private lateinit var transactionHistoryUseCase: TransactionHistoryUseCase

    @Mock
    private lateinit var transactionUseCase: TransactionUseCase

    private lateinit var viewModel: TransactionViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = TransactionViewModel(transactionHistoryUseCase, transactionUseCase)
    }

    @Test
    fun `getTransactionsHistory transactions list not empty`() = runBlocking {
        val mockTransactions = flowOf(
            listOf(
                Transactions(
                    isDeposit = true,
                    amount = 10.toBigDecimal(),
                    timeStamp = 1724324779744
                )
            )
        )
        `when`(transactionHistoryUseCase.getTransactions()).thenReturn(mockTransactions)
        viewModel.getTransactionsHistory()
        delay(50)
        assertEquals(0, viewModel.transactions.size)
        assertEquals(10.toBigDecimal(), viewModel.transactions[0].amount)
    }

    @Test
    fun `insertTransaction should call transactionUseCase`() = runBlocking {
        val transaction = Transactions(
            isDeposit = true,
            amount = 10.toBigDecimal(),
            timeStamp = 1724324779744
        )
        viewModel.insertTransaction(transaction)
        verify(transactionUseCase).doTransactions(transaction)
    }

    @Test
    fun `isAmountValidForWithDraw should return true if balance is greater than withdraw amount`() {
        viewModel.balance = 20.toBigDecimal()
        val result = viewModel.isAmountValidForWithDraw(10.toBigDecimal())
        assertTrue(result)
    }

    @Test
    fun `isAmountValidForWithDraw should return false if balance is less than withdraw amount`() {
        viewModel.balance = 5.toBigDecimal()
        val result = viewModel.isAmountValidForWithDraw(10.toBigDecimal())
        assertFalse(result)
    }

    @Test
    fun `getAvailableBalance should update availableBalance`() = runBlocking {
        val mockTransactions = flowOf(
            listOf(
                Transactions(isDeposit = true, amount = 20.toBigDecimal(), timeStamp = 12345),
                Transactions(isDeposit = false, amount = 5.toBigDecimal(), timeStamp = 67890)
            ))
        `when`(transactionHistoryUseCase.getTransactions()).thenReturn(mockTransactions)
        viewModel.getAvailableBalance()
        delay(50)
        val expectedBalance = 15.toBigDecimal()
        assertEquals(expectedBalance, viewModel.balance)
        val expectedAvailableBalance = getAmountWithCurrency(expectedBalance.toString())
        assertEquals(expectedAvailableBalance, viewModel.availableBalance.value)
    }
}