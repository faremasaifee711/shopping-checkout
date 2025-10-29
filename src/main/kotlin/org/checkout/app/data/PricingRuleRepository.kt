package org.checkout.app.data

import org.checkout.app.model.PricingRule
import org.checkout.app.model.RuleType

import java.time.Month

interface PricingRuleRepository {
    fun getAll(): List<PricingRule>
}

/**
 * Responsible for loading and providing pricing rule configurations.
 * ✅ Could later be replaced by database, API, or JSON-backed repository
 * ✅ Returns domain-friendly PricingRule models, not raw config data
 */
class InMemoryPricingRuleRepository : PricingRuleRepository {
    override fun getAll(): List<PricingRule> = listOf(
        PricingRule(
            id = 1,
            type = RuleType.MULTI_BUY,
            description = "3 for 1 Euro on Item A",
            itemName = "A",
            groupSize = 3,
            groupPrice = 1.0,
            unitPrice = 0.4
        ),
        PricingRule(
            id = 1,
            type = RuleType.MULTI_BUY,
            description = "2 for 0.80 Euros on Item B",
            itemName = "B",
            groupSize = 2,
            groupPrice = 0.8,
            unitPrice = 0.5
        ),
        PricingRule(
            id = 2,
            type = RuleType.DATE_BASED,
            description = "Black Friday 10% off",
            itemName = "E",
            month = Month.NOVEMBER,
            day = 28,
            discountPercent = 10.0,
            unitPrice = 1.0
        ),
        // --- Items without special pricing rules ---
        PricingRule(
            id = 4,
            type = null,  // No rule
            description = "Standard pricing for Item C",
            itemName = "C",
            unitPrice = 0.25
        ),
        PricingRule(
            id = 5,
            type = null,  // No rule
            description = "Standard pricing for Item D",
            itemName = "D",
            unitPrice = 0.20
        )
    )
}

