package org.checkout.app.service

import java.time.LocalDate

interface Rule<T> {
    fun matches(cart: T): Boolean
    fun apply(cart: T): Double
}


