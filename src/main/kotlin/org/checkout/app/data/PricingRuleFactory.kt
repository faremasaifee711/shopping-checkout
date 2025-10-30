package org.checkout.app.data

import java.time.Month

import org.checkout.app.service.MultiBuyRuleImpl
import org.checkout.app.service.DateBasedRuleImpl
import org.checkout.app.service.PricingRule

/**
 * The factory pattern is used to build the correct CartRule instance for each PricingRule record.
 */
object PricingRuleFactory {

    fun create(ruleType: PricingRuleType): PricingRule {
        return when (ruleType) {
            PricingRuleType.ITEM_A -> MultiBuyRuleImpl(
                itemName = "A",
                groupSize = 3,
                groupPrice = 1.0,
                unitPrice = 0.4,
                description = "3 for 1 Euro on Item A"
            )

            PricingRuleType.ITEM_B -> MultiBuyRuleImpl(
                itemName = "B",
                groupSize = 2,
                groupPrice = 0.8,
                unitPrice = 0.5,
                description = "Buy 2 for 0.80 Euro on Item B"
            )

            PricingRuleType.BLACK_FRIDAY -> DateBasedRuleImpl(
                itemName = "E",
                description = "Black Friday 10% off",
                condition = { it.month == Month.NOVEMBER && it.dayOfMonth == 28 },
                discountPercent = 10.0
            )
        }
    }
}

enum class PricingRuleType {
    ITEM_A,
    ITEM_B,
    BLACK_FRIDAY
}
