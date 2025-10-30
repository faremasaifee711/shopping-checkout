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
data class Item(
    val id: Int,
    val itemName: String,
    val unitPrice: Double,
)
