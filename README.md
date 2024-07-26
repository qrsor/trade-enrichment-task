# Trade Enrichment Service

## Prepare
1. Run `groovy generate_products.groovy` to generate a 100k `generated_product.csv` file.
2. Run `groovy generate_trade.groovy 5000000` to generate a 5M `generated_trade.csv` file.

## Start server
Run `mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xmx64m"` to start the server.
 
The memory size is set to 64MB to simulate a low memory environment.

## API documentation

See after starting the server http://localhost:8080/swagger-ui/index.html

## TODO
- [ ] Structure LineProcessor with injectable validation and enrichment strategies.
- [ ] Use lib to parse CSV files.
- [ ] Add DB to be able to limit cache size if needed.
- [ ] Improve generated API documentation.