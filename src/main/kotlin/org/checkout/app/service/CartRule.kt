package org.checkout.app.service

import org.checkout.app.service.Rule
import org.checkout.app.model.Cart

class CartRule : Rule<Cart>  {
    override fun matches(items: List<Cart>): Boolean {
        return items.size >= 3
    }

    override fun apply(items: List<Cart>): Double { 
       return items.sumByDouble { it.totalPrice }
    }
}
