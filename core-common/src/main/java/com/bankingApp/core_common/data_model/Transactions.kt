package com.bankingApp.core_common.data_model

import java.math.BigDecimal

class Transactions(
    val amount: BigDecimal,
    val isDeposit: Boolean,
    val timeStamp: Long
)