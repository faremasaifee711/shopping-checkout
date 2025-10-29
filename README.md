# kotlin-checkout-app

In this project, we delve into the low-level design of a shopping cart with offers and discounts applied as rules based on the rule-based design pattern. I walk you through a simple code implementation designing rules to be applied on a shopping cart.

## Assumptions 

## 🧾 1. Core Assumptions

### 🛍 Product & Pricing
- Each product is uniquely identified by a **single character** (`A`, `B`, `C`, `D`, `E`).
- Prices and discount rules are stored in an **in-memory `PricingRuleRepository`**.
- Some products have **special pricing rules** (e.g.,  
  `A: 3 for 1.00€`, `B: 2 for 0.80€`).
- All amounts are in **euros (€)** and stored as `Double`.
- Each item has a category (`GROCERY`, `APPLIANCES`, `FURNITURE`), though not all rules use it yet.

### 🛒 Cart & Items
- The `Cart` holds a list of `Item` objects.  
- If an existing item is added again, **quantity is updated**, not duplicated.  
- Cart `totalPrice` is recalculated each time an item is added or removed.  
- Tax is **not yet modeled** (prices are pre-tax).

### 💰 Pricing Rules
- Rules are implemented using the `Rule<T>` and `CartRule` interfaces.  
- Each rule defines how to **match** a cart condition and **apply** a discount.
- Only **one active pricing rule** applies per product (no stacking).
- Date-based rules (like Black Friday) depend on the **system date** or provided checkout date.

### 📅 Black Friday Rule
- “Black Friday” is defined as **November 28, 2025**.
- On that date, eligible products (e.g., `E`) get a **percentage discount** (10% off).

### 👤 Customer
- The `Customer` model is currently not linked to pricing rules.
- It can be extended for loyalty programs or customer-specific promotions later.

### ⚙️ System Architecture
- Built in **Kotlin (JVM)** with **Maven**.
- Follows a **modular architecture**:
  - `Rule` → defines behavior
  - `CartRule` → domain-specific logic
  - `PricingRules` → aggregates rules
  - `PricingRuleRepository` → stores product rules
- Designed to support **easy extension** (new rule types, categories, etc.)
- No persistence layer or external data store (all data is in-memory).

---

## ⚖️ 2. Tradeoffs

| Area | Design Choice | Tradeoff |
|------|----------------|-----------|
| **Data Storage** | In-memory repository | Fast and simple but no persistence |
| **Pricing Precision** | Uses `Double` | May introduce floating-point rounding errors |
| **Rule Engine** | Simple interface-based | Harder to express complex multi-item rules |
| **Black Friday Handling** | Hardcoded date (Nov 28, 2025) | Needs update each year |
| **Single Rule per Product** | Simplifies calculation | Prevents combined discounts (e.g., loyalty + holiday) |
| **No Tax / Currency Handling** | Keeps model simple | Limited real-world applicability |
| **No Validation Layer** | Easy prototyping | Possible invalid states (negative quantity, missing rule) |

---

## 🚀 3. Possible Extensions

### 🧩 Feature Enhancements
- Add **tax and currency support**.  
- Add **multi-buy or combo deals** (e.g., “Buy A and B for 1.20€”).
- Support **stackable or prioritized rules** (e.g., Black Friday + Member discount).
- Integrate **Customer loyalty programs**.
- Add **promo codes or coupons**.

### 💾 Data & Storage
- Replace in-memory `PricingRuleRepository` with a **database-backed** implementation (e.g., PostgreSQL or SQLite).  
- Use **JSON or YAML** for rule configuration to allow non-developers to update prices.

### 📦 Cart Improvements
- Add validation to prevent negative quantities or unknown items.
- Add methods for **removeItem**, **updateQuantity**, and **clearCart**.
- Implement **total recalculation** via computed property (`get() = items.sumOf {...}`).

### 🧠 Rule Engine Extensions
- Introduce a **rule priority system**.  
- Add **time-bound rules** (“valid between dates”).  
- Add **category-level discounts** (e.g., “10% off all furniture”).  
- Allow **chained rules** for more complex business logic.

### 🌐 Integration & UI
- Expose a **REST API** (using Ktor or Spring Boot).  
- Add a **frontend** for checkout simulation.  
- Support **import/export** of pricing rules.

---

## Commands mvn

Command to build and Run tests from src/test/kotlin/org/checkout/app/service/CheckoutTest.kt

```
mvn clean package   
```

---

© 2025 Checkout App — Kotlin/Maven Example Project



