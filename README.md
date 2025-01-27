# SmartFinance View

The **SmartFinance View** is a modern and intuitive user interface built with **Vaadin**. 
It serves as the front end for the **SmartFinance REST API**, providing users with tools to 
manage their finances effectively, track investments, and view essential financial data.

## Features

- **Expense Tracking**: Record and manage income and expenses to monitor your financial status.
- **Investments**: Seamlessly invest in currencies and cryptocurrencies.
- **Live Rates**: Check the latest exchange rates for currencies and cryptocurrencies.
- **Transaction History**: Browse through all historical transactions.
- **Assets**: See an up-to-date list of all available assets in your portfolio.
- **Secure Login**: Use the secure login form to authenticate and access your account.

## Setup and Running the Application

1. **Clone the Backend Repository**  
   ```
   git clone https://github.com/opach16/SmartFinance.git
   ```
   
2. **Configure the Database**  
Update the `application.properties` file with your PostgreSQL credentials:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/SmartFinanceDB
spring.datasource.username=<your-database-username>
spring.datasource.password=<your-database-password>
```

3. **Add API Keys**  
Include your API keys for CoinGecko and CurrencyAPI.com in the `application.properties` file:
```
coingecko.api.key=<your-coingecko-api-key>
currencyapi.api.key=<your-currencyapi-key>
```

4. **Build and Run the Backend**  
Use Gradle to build and start the backend application:
```
./gradlew build
./gradlew bootRun
```

5. **Clone the Frontend Repository**
   ```
   git clone https://github.com/opach16/SmartFinance_view.git
   ```
   
6. **Run the Frontend**  
Navigate to the Vaadin frontend directory and start the frontend application:
```
./gradlew build
./gradlew bootRun
```

7. **Access the App**  
- **Frontend**: http://localhost:8081/
- **API Documentation**: http://localhost:8080/swagger-ui/index.html


## Contact

For any questions or support, contact:
- **Author**: Konrad
- **Email**: [opach16@outlook.com]
