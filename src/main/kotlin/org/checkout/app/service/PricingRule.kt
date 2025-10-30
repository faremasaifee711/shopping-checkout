package org.checkout.app.service

import org.checkout.app.model.Cart

/**
 * Defines the contract for all pricing rules.
 */
interface PricingRule : Rule<Cart>  {
}
