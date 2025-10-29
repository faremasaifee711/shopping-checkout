package org.checkout.app

import java.time.LocalDateTime
import java.time.Month

import org.checkout.app.model.Cart
import org.checkout.app.model.Category
import org.checkout.app.model.Item
import org.checkout.app.model.Customer
import org.checkout.app.model.PricingRule
import org.checkout.app.service.PricingRules


class Checkout(private val pricingRules: PricingRules, private val customer: Customer) {
    private val cart = Cart(customer = customer)

    init {
        println("Beginning Checkout, Inserting pricing rules.")
        // Add any additional initialization logic here
    }
    

    // Scans an item by SKU and adds it to cart
    fun scan(sku: String) {
        // locate item by SKU, here you need a repository or lookup mechanism
        val item = findItemBySku(sku)
        if (item != null) {
            cart.addItem(item, pricingRules.pricingRules)
        } else {
            println("Item with SKU $sku not found")
        }
    }

    fun scanOnBlackFriday(sku: String) {
        cart.createdAt = LocalDateTime.of(2025, Month.NOVEMBER, 28, 0, 0)
        scan(sku)

    }

    // Calculates and returns the total cart price in cents
    fun total(): Int {
        //cart.checkoutCart(pricingRules)
        // Converts totalPrice to cents as integer
        return (cart.totalPrice * 100).toInt()
        
    }

    // Actual lookup logic, from in-memory list
    private fun findItemBySku(sku: String): Item? {

        val pricingRule: PricingRule? = pricingRules.pricingRules
            .firstOrNull { rule -> rule.itemName.equals(sku, ignoreCase = true) }
            
        return pricingRule?.let { rule ->
            Item(
                name = rule.itemName,
                price = rule.unitPrice,
                quantity = 1,  // Default quantity
                category = Category.GROCERY // Replace with actual category logic
            )
        }
        
    }
}
