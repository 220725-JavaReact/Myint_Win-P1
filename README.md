# Myint_Win-P1
Project-1 Fashion Store
I use servlets and create a REST API of store app.

## List of REST API and sample CURL
* /FashionStore/registerCustomer
```
curl --location --request POST 'http://localhost:8080/FashionStore/registerCustomer' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"Win Thurein Myint",
    "email":"thurein@mail.com",
    "address":"Bentonville, AR"
}'
```
* /FashionStore/searchCustomer
```
curl --location --request GET 'http://localhost:8080/FashionStore/searchCustomer?email=win@mail.com'
```
* /FashionStore/allCustomer
```
curl --location --request GET 'http://localhost:8080/FashionStore/allCustomer'
```
* /FashionStore/getAllStore
```
curl --location --request GET 'http://localhost:8080/FashionStore/getAllStore'
```
* /FashionStore/getStoreByName
```
curl --location --request GET 'http://localhost:8080/FashionStore/getStoreByName?name=XYZ Fifth Avenue Store'
```
* /FashionStore/getStoreByAddress
```
curl --location --request GET 'http://localhost:8080/FashionStore/getStoreByAddress?address=Bronx'
```
* /FashionStore/replenishStoreInventory
```
curl --location --request POST 'http://localhost:8080/FashionStore/replenishStoreInventory' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "XYZ Fifth Avenue Store",
    "product": [
        {
            "product": {
                "name": "Leather Jacket"
            },
            "quantity": 10
        },
        {
            "product": {
                "name": "Socks"
            },
            "quantity": 10
        }
    ]
}'
```
* /FashionStore/order
```
curl --location --request POST 'http://localhost:8080/FashionStore/order' \
--header 'Content-Type: application/json' \
--data-raw '{
                "email": "win@mail.com",
                "productName": "Activeware",
                "storeName": "XYZ Fifth Avenue Store",
                "quantity": 1
}'
```
## Project requirement

### Functionalities:

    Add customer
    View Store inventory
    Search customer
    Place order
    Order History
    Replenish Inventory

### Implementation:

    Use servlets and create a REST API to accomplish these fcnalities
    Use servlets and create a server side rendering web app
    Use servlets to create 2 separate projects, one a rest api that serves info to the server side rendering application

Note: You can choose one implementation of the project
### Features:

    Logging
    Testing
    DB Persistence

### Stretch goals:

    Application should be deployed on an AWS EC2

### Tech stack:

    Servlet API
    JDBC
    PostgreSQL
    Java
    Junit
    Log4j
    
