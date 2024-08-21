package com.zeller.terminalapp

import java.math.BigDecimal

class Transactions(
    val amount: BigDecimal,
    val isDeposit: Boolean,
    val timeStamp: Long
)