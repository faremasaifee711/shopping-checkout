package org.checkout.app.service

import java.time.LocalDateTime
import java.time.Month
import org.checkout.app.service.DateBasedRule
import org.checkout.app.model.Cart
import org.checkout.app.model.Item
import org.checkout.app.model.Category

class CyberMondayRuleImpl(private val desc: String) : DateBasedRule {

    fun isCyberMonday(date: LocalDateTime): Boolean {
        return  date.month == Month.NOVEMBER && date.dayOfMonth == 31
    }

    override fun matches(cart: Cart): Boolean {
        // Example: return true if any date matches Black Friday
        return isCyberMonday(cart.createdAt) //cart.items.any { isBlackFriday(it) }
    }

    override fun apply(cart: Cart): Double {
        // 10% off on BFSale
        println("Applying Rule: " + desc)
        val currentPrice = cart.totalPrice
        cart.totalPrice = currentPrice * 0.95
        cart.addItem(Item("Samsung Headphone", 20.50, 1, Category.APPLIANCES))
        return cart.items.size.toDouble()
    }
}