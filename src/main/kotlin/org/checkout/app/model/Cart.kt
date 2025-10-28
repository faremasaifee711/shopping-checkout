package org.checkout.app.model

import org.checkout.app.model.Item
import org.checkout.app.model.Customer
import java.time.LocalDateTime

import org.checkout.app.service.RuleEngine

data class Cart(
    val items: MutableList<Item> = mutableListOf(),
    val customer: Customer,
    var totalPrice: Double = 0.0, //val totalPrice: Double get() = items.sumOf { it.totalCostAfterTax() } //can use this instead of calculateCartlPrice()
    val createdAt: LocalDateTime = LocalDateTime.now()
) {
    fun addItem(item: Item) {
        items.add(item)
        calculateCartlPrice()
    }

    fun removeItem(item: Item) {
        items.remove(item)
        calculateCartlPrice()
    }

    fun calculateCartlPrice() {
        this.totalPrice = items.sumByDouble { it.totalCostAfterTax() }
    }

    fun checkoutCart(ruleEngine: RuleEngine) {
        println("Checking if any offer applicable on final cart")
        this.totalPrice = items.sumByDouble { it.totalCostAfterTax() }
        println("Before Offer : " +  this.totalPrice)
        ruleEngine.cartRules
            .filter { rule -> 
                rule.matches(this)
            }
            .forEach { matchingRule -> 
                matchingRule.apply(this) 
            }

    }
    
    override fun toString(): String {
        return "Cart(items=$items, customer=$customer, totalPrice=$totalPrice, createdAt=$createdAt)"
    }
}

//Usage:

// val cart = Cart(items = listOf(Item("Sofa", 800.0, 1, Category.FURNITURE)), customer = Customer("John Doe"), totalPrice = 0.0, createdAt = LocalDateTime.now())
// println("Cart: $cart")