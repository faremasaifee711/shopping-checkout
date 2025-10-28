package org.checkout.app

import org.checkout.app.service.PricingRules
import org.checkout.app.service.BlackFridayRuleImpl
import org.checkout.app.service.CyberMondayRuleImpl
import org.checkout.app.model.Account
import org.checkout.app.model.Cart
import org.checkout.app.model.Category
import org.checkout.app.model.Customer
import org.checkout.app.model.Item

fun main() {
    println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$")
    println("starting app execution")
    println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$")

    val pricingRules = PricingRules()
    val blackFridayRule = BlackFridayRuleImpl("Black Friday Offer")
    val cyberMondayRule = CyberMondayRuleImpl("Cyber Monday Offer")

    pricingRules.addRule(blackFridayRule)
    pricingRules.addRule(cyberMondayRule)

    val account = Account("A1", "name", "xyz@email.com", "abcd1234")
    val customer =  Customer("C1", "John Doe", account)

    val currentCart = buildCartForCustomer(customer);
    
}

fun buildCartForCustomer(customer: Customer) : Cart {
  val itemList: MutableList<Item> = mutableListOf()
  itemList.add(Item("Apple", 1.09, 12, Category.GROCERY)) 
  itemList.add(Item("Iphone 12", 1076.25, 1, Category.APPLIANCES)) 
  itemList.add(Item("Sofa", 800.0, 1, Category.FURNITURE)) 
    
  val cart = Cart(itemList, customer)

  return cart;
}
  
  