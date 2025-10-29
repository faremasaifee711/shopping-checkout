package org.checkout.app.service

import org.checkout.app.service.CartRule

/**
 * Generic implementation of quantity-based pricing (e.g. 3 for 1Euro). 
 * Reusable for multiple items
 *  Configuration-driven behavior
 */
interface MultiBuyRule : CartRule {
}
