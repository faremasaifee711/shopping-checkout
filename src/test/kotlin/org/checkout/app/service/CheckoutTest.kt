package org.checkout

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class CheckoutTest {
    @Test
    fun testMain() {
        assertEquals(1, 1)
    }

    /**
    
    private val rules = sampleRules() // your builder/factory
    
    private fun priceOf(goods: String): Int {
        val co = Checkout(rules)
        goods.forEach { co.scan(it.toString()) }
        return co.total()
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