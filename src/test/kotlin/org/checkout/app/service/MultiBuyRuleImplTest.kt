package org.checkout.app.service

import org.checkout.app.model.Account
import org.checkout.app.model.Cart
import org.checkout.app.model.CartItem
import org.checkout.app.model.Customer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MultiBuyRuleImplTest {

    private val rule = MultiBuyRuleImpl(
        itemName = "Apple",
        groupSize = 3,
        groupPrice = 5.0,
        unitPrice = 2.0,
        description = "Buy 3 Apples for 5.0"
    )

    private val account = Account("A1", "name", "xyz@email.com", "abcd1234")
    private val customer =  Customer("C1", "John Doe 1", account)

    @Test
    fun `matches should return true when cart contains matching item`() {
        val cart = Cart(mutableListOf(CartItem("Apple", 3.0, 2)), customer)
        assertTrue(rule.matches(cart))
    }

    @Test
    fun `matches should return false when cart has no matching item`() {
        val cart = Cart(mutableListOf(CartItem("Banana", 2.0, 1)), customer)
        assertFalse(rule.matches(cart))
    }

    @Test
    fun `apply should return zero discount when item quantity less than group size`() {
        val cart = Cart(mutableListOf(CartItem("Apple", 2.5, 2)), customer)
        val discount = rule.apply(cart)
        assertEquals(0.0, discount)
    }

    @Test
    fun `apply should return zero discount when item not found`() {
        val cart = Cart(mutableListOf(CartItem("Banana", 5.0, 1)), customer)
        val discount = rule.apply(cart)
        assertEquals(0.0, discount)
    }
}