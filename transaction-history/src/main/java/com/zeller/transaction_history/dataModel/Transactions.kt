package com.zeller.transaction_history.dataModel

import java.math.BigDecimal

class Transactions(
    val amount: BigDecimal,
    val isDeposit: Boolean,
    val timeStamp: Long
)