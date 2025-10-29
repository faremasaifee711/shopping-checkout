package org.checkout.app.service

import org.checkout.app.service.Rule
import org.checkout.app.model.Cart

/**
 * Defines the contract for all pricing rules.
 */
interface CartRule : Rule<Cart>  {
}
