package com.zeller.injector.transaction_history

import com.zeller.transaction_history.viewModel.TransactionViewModel
import com.zeller.transaction_history.repository.TransactionHistoryRepository
import com.zeller.transaction_history.repository.TransactionRepository
import com.zeller.transaction_history.usecase.TransactionHistoryUseCase
import com.zeller.transaction_history.usecase.TransactionUseCase
import com.zeller.transaction_history_impl.repository.TransactionHistoryRepositoryImpl
import com.zeller.transaction_history_impl.usecase.TransactionHistoryUseCaseImpl
import com.zeller.transaction_history_impl.usecase.TransactionRepositoryImpl
import com.zeller.transaction_history_impl.usecase.TransactionUseCaseImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun getTransactionHistoryModules(): List<Module> {

    val transactionHistoryRepository = module {
        single<TransactionHistoryRepository> { TransactionHistoryRepositoryImpl(get()) }
    }

    val transactionRepository = module {
        single<TransactionRepository> { TransactionRepositoryImpl(get()) }
    }

    val transactionHistoryUseCase =
        module { single<TransactionHistoryUseCase> { TransactionHistoryUseCaseImpl(get()) } }

    val transactionUseCase =
        module { single<TransactionUseCase> { TransactionUseCaseImpl(get()) } }

    val transactionsViewModel = module { viewModel { TransactionViewModel(get(), get()) } }

    return listOf(
        transactionHistoryRepository,
        transactionRepository,
        transactionHistoryUseCase,
        transactionUseCase,
        transactionsViewModel
    )

}