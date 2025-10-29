package org.checkout.app

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
            cart.addItem(item)
        } else {
            println("Item with SKU $sku not found")
        }
    }

    // Calculates and returns the total cart price in cents
    fun total(): Int {
        //cart.checkoutCart(pricingRules)
        // Converts totalPrice to cents as integer
        // return (cart.totalPrice * 100).toInt()

        //return pricingRules.cartRules.sumOf { rule ->
         //   if (rule.matches(cart)) (rule.apply(cart) * 100).toInt() else 0
        //}
        return 1
        
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
