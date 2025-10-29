package org.checkout.app.model

data class Customer(
    val id: String,
    val name: String,
    val account: Account
) 