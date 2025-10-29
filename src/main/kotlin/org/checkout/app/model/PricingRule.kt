package org.checkout.app.model

import java.time.LocalDateTime
import java.time.Month

/**
 * Represents a product pricing rule that defines how the price is calculated or discounted
 * based on specific conditions such as bulk quantity or date-based discounts.
 *
 * @property itemName The unique identifier for the product (typically a SKU or a code).
 * @property unitPrice The standard price for a single unit of the product.
 * @property groupSize Optional minimum quantity for bulk discount eligibility.
 * @property groupPrice Optional price per group of items for bulk pricing.
 * @property discountPercent Optional percentage discount applied on special dates.
 * @property day Optional date when the discount is valid.
 */
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
