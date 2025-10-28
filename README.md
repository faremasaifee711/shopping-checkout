# kotlin-checkout-app

In this project, we delve into the low-level design of a shopping cart with offers and discounts applied as rules based on the rule-based design pattern. I walk you through a simple code implementation designing rules to be applied on a shopping cart.

Includes:

- --JDBI for database manipulation--
- Ktorm as ORM
- Javalin for http server
- Lettuce as a redis client
- Caffeine as an in memory cache
- Unirest as an http client
- Jib to create docker images [mvn compile jib:dockerBuild]
- Maven assembly plugin to create fat jar [mvn package]
- Codehaus to run the app directly from terminal [mvn clean compile exec:java]

# Assumptions 

Includes:

- first assumption

# Trade-offs

Includes:

- first trade-off

# Possible Extensions

Includes:

- First Expension

https://www.youtube.com/watch?v=nXBW9Sdy4cY

Commands mvn

mvn clean package   
mvn exec:java -Dexec.mainClass="org.checkout.app.AppKt"



