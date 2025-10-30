package org.checkout.app.model

import java.time.LocalDateTime

import org.checkout.app.data.PricingRuleFactory
import org.checkout.app.data.PricingRuleType
import org.checkout.app.service.PricingRule

/**
 * The shopping cart which holds items selected by the customer.
 * Responsible for managing items, updating quantities, and calculating the total price.
 *
 * @property cartItems The mutable list of items currently in the cart.
 * @property customer The customer associated with this cart.
 * @property totalPrice The current total price of the cart, accounting for discounts and pricing rules.
 * @property createdAt The date and time when the cart is created
 * @property rules The Pricing rules which can determine discounts to be applied on total price of cart
 */
data class Cart(
    val cartItems: MutableList<CartItem> = mutableListOf(),
    val customer: Customer,
    var totalPrice: Double = 0.0,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var rules : List<PricingRule> = listOf(),
) {

    init {
        this.rules = listOf(
            PricingRuleFactory.create(PricingRuleType.ITEM_A),
            PricingRuleFactory.create(PricingRuleType.ITEM_B),
            PricingRuleFactory.create(PricingRuleType.BLACK_FRIDAY)
        )
    }

    /**
     * Adds an item to the cart. If the item already exists, updates the quantity and recalculates price based on pricing rules.
     *
     * @param cartItem The item to add or update in the cart.
     * @param pricingRules List of pricing rules to apply for pricing adjustments and discounts.
     */
    fun addItem(cartItem: CartItem) {
        val existingItemIndex = cartItems.indexOfFirst { it.name.equals(cartItem.name, ignoreCase = true) }

        if (existingItemIndex != -1) {
            val existingItem = cartItems[existingItemIndex]
            val newQuantity = existingItem.quantity + cartItem.quantity
            cartItems[existingItemIndex] = existingItem.copy(quantity = newQuantity)
        } else {
            cartItems.add(cartItem)
        }

        calculateCartPrice()
    }

    fun removeItem(cartItem: CartItem) {
        cartItems.remove(cartItem)
        calculateCartPrice()
    }

    /**
     * Calculates the total price of the cart by summing the price * quantity for all items.
     * Applies pricing rules on the cart
     * Should be called whenever the cart's content changes to keep the total price accurate.
     */
    fun calculateCartPrice() {
        this.totalPrice = cartItems.sumOf { it.totalCostAfterTax() }
        this.applyPricingRules()
    }

    fun applyPricingRules() {
        val baseTotal = cartItems.sumOf { it.price * it.quantity }
        val totalDiscount = this.rules.filter { it.matches(this) }
            .sumOf { it.apply(this) }
        this.totalPrice = baseTotal - totalDiscount
    }
    
    override fun toString(): String {
        return "Cart(items=$cartItems, customer=$customer, totalPrice=$totalPrice, createdAt=$createdAt)"
    }
}