import org.checkout.app.service.CartRule

class RuleEngine(
    val cartRules: MutableList<CartRule> = mutableListOf()
) {
    fun addRule(cartRule: CartRule) {
        cartRules.add(cartRule)
    }
}