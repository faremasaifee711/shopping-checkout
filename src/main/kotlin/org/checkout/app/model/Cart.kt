package org.checkout.app.model

import org.checkout.app.model.Item
import org.checkout.app.model.Customer
import java.time.LocalDateTime

data class Cart(
    val items: MutableList<Item> = mutableListOf(),
    val customer: Customer,
    var totalPrice: Double = 0.0, //val totalPrice: Double get() = items.sumOf { it.totalCostAfterTax() } //can use this instead of calculateCartlPrice()
    val createdAt: LocalDateTime = LocalDateTime.now()
) {
    fun addItem(item: Item) {
        // Find if the item already exists by name
        val existingItemIndex = items.indexOfFirst { it.name == item.name }

        if (existingItemIndex != -1) {
            // Update the existing item quantity
            val existingItem = items[existingItemIndex]
            val updatedItem = existingItem.copy(
                quantity = existingItem.quantity + item.quantity
            )
            items[existingItemIndex] = updatedItem
        } else {
            // Add as a new item
            items.add(item)
        }
        calculateCartlPrice()
    }

    fun removeItem(item: Item) {
        items.remove(item)
        calculateCartlPrice()
    }

    fun calculateCartlPrice() {
        this.totalPrice = items.sumOf { it.totalCostAfterTax() }
    }
    
    override fun toString(): String {
        return "Cart(items=$items, customer=$customer, totalPrice=$totalPrice, createdAt=$createdAt)"
    }
}

//Usage:

