package org.checkout.app.service

interface Rule<T> {
    fun matches(cart: T): Boolean
    fun apply(cart: T): Double
}


