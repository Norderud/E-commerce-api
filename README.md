# Simple E-Commerce API

### Description
This is a simple E-Commerce API with a single endpoint: <b>/checkout</b>. <br>
The checkout endpoint should take a list of products and return the total cost of the products after discounts have been calculated. 

### How to build and run
<b>1. Clone the repository:</b> git clone https://github.com/Norderud/BankID_e-commerce-api.git <br>
<b>2. Build the project:</b> mvn clean install <br>
<b>3. Run the project:</b> mvn spring-boot:start or directly from your IDEA.  <br>

### Testing the endpoint
You can test the endpoint by either using a tool such as postman or run the following curl command in your terminal: <br>
```curl -X POST http://localhost:8080/api/v1/checkout -H "Content-Type: application/json" -d '["0001","0001","0001","0004"]'``` <br>

### Potential improvements
- Error handling and validation.
- Implement a database to store products and discounts.
- Logging and monitoring.
- Implement a discount strategy pattern to encapsulate various discount rules.
- Implement more endpoints for adding, updating and deleting products.
- Security and authentication.