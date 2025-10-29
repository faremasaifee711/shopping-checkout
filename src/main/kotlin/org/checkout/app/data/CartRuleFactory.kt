package org.checkout.app.data

import java.time.LocalDateTime
import java.time.Month

import org.checkout.app.model.Item
import org.checkout.app.service.MultiBuyRuleImpl
import org.checkout.app.service.DateBasedRuleImpl
import org.checkout.app.service.CartRule

/**
 * The factory pattern is used to build the correct CartRule instance for each PricingRule record.
 */
object CartRuleFactory {

    fun create(ruleType: CartRuleType): CartRule {
        return when (ruleType) {
            CartRuleType.ITEM_A -> MultiBuyRuleImpl(
                itemName = "A",
                groupSize = 3,
                groupPrice = 1.0,
                unitPrice = 0.4,
                description = "3 for 1 Euro on Item A"
            )

            CartRuleType.ITEM_B -> MultiBuyRuleImpl(
                itemName = "B",
                groupSize = 2,
                groupPrice = 0.8,
                unitPrice = 0.5,
                description = "Buy 2 for 0.80 Euro on Item B"
            )

            CartRuleType.BLACK_FRIDAY -> DateBasedRuleImpl(
                itemName = "E",
                description = "Black Friday 10% off",
                condition = { it.month == Month.NOVEMBER && it.dayOfMonth == 28 },
                discountPercent = 10.0
            )
        }
    }
}

enum class CartRuleType {
    ITEM_A,
    ITEM_B,
    BLACK_FRIDAY
}
