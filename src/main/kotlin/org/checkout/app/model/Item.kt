package org.checkout.app.model

data class Item(
    val name: String,
    val price: Double,
    val quantity: Int,
    val category: Category
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

//can add sku: String here as well

//Usage:

// val item = Item("Sofa", 800.0, 1, Category.FURNITURE)
// println("Item: ${item.name}, Total cost: ${item.totalCostAfterTax()}, Category: ${item.category}")

enum class Category(val taxRate: Double, val discountRate: Double) {
    GROCERY(taxRate = 0.05, discountRate = 0.0),
    APPLIANCES(taxRate = 0.18, discountRate = 0.10),
    FURNITURE(taxRate = 0.12, discountRate = 0.05)
}