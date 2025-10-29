package org.checkout

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

import org.checkout.app.model.Account
import org.checkout.app.model.Cart
import org.checkout.app.model.Category
import org.checkout.app.model.Customer
import org.checkout.app.model.Item

import org.checkout.app.Checkout
import org.checkout.app.data.InMemoryPricingRuleRepository
import org.checkout.app.service.PricingRules



class CheckoutTest {
    @Test
    fun testMain() {
        assertEquals(1, 1)
    }

    private fun sampleRules() :  PricingRules {
        val pricingRuleRepository = InMemoryPricingRuleRepository()
        return PricingRules(pricingRuleRepository.getAll())
    }

    private val account = Account("A1", "name", "xyz@email.com", "abcd1234")
    private val customer =  Customer("C1", "John Doe", account)

    private val rules = sampleRules() // your builder/factory
    
    private fun priceOf(goods: String): Int {
        val co = Checkout(rules, customer)
        goods.forEach { co.scan(it.toString()) }
        return co.total()
    }

    fun buildCartForCustomer(customer: Customer) : Cart {
        val itemList: MutableList<Item> = mutableListOf()
        itemList.add(Item("Apple", 1.09, 12, Category.GROCERY)) 
        itemList.add(Item("Iphone 12", 1076.25, 1, Category.APPLIANCES)) 
        itemList.add(Item("Sofa", 800.0, 1, Category.FURNITURE)) 
          
        val cart = Cart(itemList, customer)
      
        return cart;
    }

    @Test fun totals() {
        assertEquals(0, priceOf(""))
        assertEquals(40, priceOf("A"))
        assertEquals(90, priceOf("AB"))
        assertEquals(135, priceOf("CDBA"))
        assertEquals(80, priceOf("AA"))
        assertEquals(100, priceOf("AAA"))
        assertEquals(140, priceOf("AAAA"))
        assertEquals(180, priceOf("AAAAA"))
        assertEquals(200, priceOf("AAAAAA"))
        assertEquals(150, priceOf("AAAB"))
        assertEquals(180, priceOf("AAABB"))
        assertEquals(200, priceOf("AAABBD"))
        assertEquals(200, priceOf("DABABA"))
    }

    /**

    @Test fun incremental() {
        val co = Checkout(rules)
        assertEquals(0, co.total())
        co.scan("A"); assertEquals(40, co.total())
        co.scan("B"); assertEquals(90, co.total())
        co.scan("A"); assertEquals(130, co.total())
        co.scan("A"); assertEquals(150, co.total())
        co.scan("B"); assertEquals(180, co.total())
    }
    @Test fun skuE_blackFridayDiscount() {
        // Add test for 10% off E on November 28, 2025
    }

    */
}