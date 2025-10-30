package org.checkout.app.data

import org.checkout.app.model.Item
import java.time.Month

interface ItemRepository {
    fun getAll(): List<Item>
}

/**
 * Responsible for loading and providing pricing rule configurations.
 * ✅ Could later be replaced by database, API, or JSON-backed repository
 * ✅ Returns domain-friendly PricingRule models, not raw config data
 */
class InMemoryItemRepository : ItemRepository {
    override fun getAll(): List<Item> = listOf(
        Item(
            id = 1,
            itemName = "A",
            unitPrice = 0.4
        ),
        Item(
            id = 1,
            itemName = "B",
            unitPrice = 0.5
        ),
        Item(
            id = 2,
            itemName = "E",
            unitPrice = 1.0
        ),
        // --- Items without special pricing rules ---
        Item(
            id = 4,
            itemName = "C",
            unitPrice = 0.25
        ),
        Item(
            id = 5,
            itemName = "D",
            unitPrice = 0.20
        )
    )
}

