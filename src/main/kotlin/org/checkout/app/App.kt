package org.checkout.app

import org.checkout.app.service.PriceRuleImpl

fun main() {
    val service = PriceRuleImpl()
    val result = service.apply(listOf("A", "B", "C"))
    println("Processed result: $result")
}
  
  