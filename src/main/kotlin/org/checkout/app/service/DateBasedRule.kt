package org.checkout.app.service

import java.time.LocalDate
import org.checkout.app.service.Rule
import org.checkout.app.model.Cart

interface DateBasedRule : CartRule {
    //fun isBlackFriday(cart: Cart): Boolean
}

