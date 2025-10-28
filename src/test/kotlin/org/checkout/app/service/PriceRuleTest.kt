package org.checkout.app.service

import org.junit.Test
import kotlin.test.assertEquals

class PriceRuleTest {

    private val service = PriceRuleImpl()

    @Test
    fun `processData should trim and uppercase input`() {
        val input = listOf(" test ")
        val result = service.apply(input)
        assertEquals("TEST", result)
    }
}