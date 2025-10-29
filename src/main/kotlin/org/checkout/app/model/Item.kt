package org.checkout.app.model

data class Item(
    val name: String,
    val price: Double,
    val quantity: Int,
    val category: Category = Category.GROCERY
) {
    fun totalCostBeforeTax(): Double {
        val discountedPrice = price * (1 - category.discountRate)
        return discountedPrice * quantity
    }

    fun totalCostAfterTax(): Double {
        val base = totalCostBeforeTax()
        return base * (1 + category.taxRate)
    }
}

// For future we can add more categories to have a better tax system, or discounts across the entire category
enum class Category(val taxRate: Double, val discountRate: Double) {
    GROCERY(taxRate = 0.00, discountRate = 0.0),
    APPLIANCES(taxRate = 0.18, discountRate = 0.10),
    FURNITURE(taxRate = 0.12, discountRate = 0.05)
}