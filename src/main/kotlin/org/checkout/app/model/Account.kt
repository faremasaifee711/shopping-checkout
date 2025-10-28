package org.checkout.app.model

data class Account(
    val id: String,
    val name: String,
    val email: String,
    val password: String
) {
    fun login(email: String, password: String): Boolean {
        return this.email == email && this.password == password
    }
}