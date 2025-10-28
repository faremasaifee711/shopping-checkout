package org.checkout.app.service

import java.time.LocalDate

interface Rule<T> {
    fun matches(item: T): Boolean
    fun apply(item: T): Double
}


