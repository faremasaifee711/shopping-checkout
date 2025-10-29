package org.checkout.app.model
import java.time.LocalDateTime

import java.time.Month

data class PricingRule(
    val id: Int,
    val type: RuleType? = null,
    val description: String,
    val itemName: String,
    val groupSize: Int? = null,
    val unitPrice: Double,
    val groupPrice: Double? = null,
    val month: Month? = null,
    val day: Int? = null,
    val discountPercent: Double? = null
)

enum class RuleType {
    MULTI_BUY,
    DATE_BASED
}
