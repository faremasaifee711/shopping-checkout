package org.checkout.app.service

import org.checkout.app.model.Account
import org.checkout.app.model.Cart
import org.checkout.app.model.CartItem
import org.checkout.app.model.Customer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class DateBasedRuleImplTest {

    private val now = LocalDateTime.now()
    private val account = Account("A1", "name", "xyz@email.com", "abcd1234")
    private val customer =  Customer("C1", "John Doe 1", account)

    @Test
    fun `matches should return true when condition is met`() {
        val condition: (LocalDateTime) -> Boolean = { true }
        val rule = DateBasedRuleImpl("Apple", "Black Friday 10%", condition, 10.0)
        val cart = Cart(mutableListOf(CartItem("Apple", 2.0, 2)), customer, createdAt = now)
        assertTrue(rule.matches(cart))
    }

    @Test
    fun `matches should return false when condition is not met`() {
        val condition: (LocalDateTime) -> Boolean = { false }
        val rule = DateBasedRuleImpl("Apple", "Weekend Sale", condition, 10.0)
        val cart = Cart(mutableListOf(CartItem("Apple", 2.0, 2)), customer, createdAt = now)
        assertFalse(rule.matches(cart))
    }

    @Test
    fun `apply should calculate discount when matching item exists`() {
        val condition: (LocalDateTime) -> Boolean = { true }
        val rule = DateBasedRuleImpl("Apple", "10% off", condition, 10.0)
        val cart = Cart(mutableListOf(CartItem("Apple", 5.0, 2)), customer, createdAt = now)
        // price = 5 * 2.0 = 10.0, 10% discount = 1.0
        val discount = rule.apply(cart)
        assertEquals(1.0, discount)
    }

    @Test
    fun `apply should return zero discount when item not found`() {
        val condition: (LocalDateTime) -> Boolean = { true }
        val rule = DateBasedRuleImpl("Orange", "10% off", condition, 10.0)
        val cart = Cart(mutableListOf(CartItem("Apple", 3.0, 2)), customer, createdAt = now)
        val discount = rule.apply(cart)
        assertEquals(0.0, discount)
    }
}
