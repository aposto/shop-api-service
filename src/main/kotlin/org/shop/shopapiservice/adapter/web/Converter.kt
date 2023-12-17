package org.shop.shopapiservice.adapter.web;

import java.text.DecimalFormat

private val fmt = DecimalFormat("#,###")
fun Int.toMoney(): String = fmt.format(this)
