package org.checkout.app

import org.checkout.app.model.Cart
import org.checkout.app.model.Item
import org.checkout.app.model.Customer
import org.checkout.app.service.PricingRules

class Checkout(private val pricingRules: PricingRules, private val customer: Customer) {
    private val cart = Cart(customer = customer)

    // Scans an item by SKU and adds it to cart
    fun scan(sku: String) {
        // locate item by SKU, here you need a repository or lookup mechanism
        val item = findItemBySku(sku)
        if (item != null) {
            cart.addItem(item)
        } else {
            println("Item with SKU $sku not found")
        }
    }

    // Calculates and returns the total cart price in cents
    fun total(): Int {
        cart.checkoutCart(pricingRules)
        // Converts totalPrice to cents as integer
        return (cart.totalPrice * 100).toInt()
    }

    // Stub for item lookup (replace with actual item repository logic)
    private fun findItemBySku(sku: String): Item? {
        // TODO: You need to implement actual lookup logic, possibly from DB or in-memory list
        return null // placeholder
    }
}
