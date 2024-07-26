import java.time.LocalDate
import java.time.format.DateTimeFormatter

def generateCSV(String fileName, int numRows) {
    def random = new Random()
    def currencies = ['EUR', 'NOK', 'SEK', 'DKK', 'PLN']

    new File(fileName).withWriter { writer ->
        writer.writeLine("date,product_id,currency,price")

        numRows.times {
            def date = generateDate(random)
            def productId = random.nextInt(200000) + 1
            def currency = currencies[random.nextInt(currencies.size())]
            def price = (random.nextFloat() * 499 + 1).round(2)

            writer.writeLine("$date,$productId,$currency,$price")
        }
    }

    println "CSV file '$fileName' with $numRows rows has been generated."
}

def generateDate(Random random) {
    if (random.nextInt(100) < 10) {  // 10% chance of empty or invalid date
        return random.nextBoolean() ? "" : "InvalidDate"
    }

    LocalDate.now()
            .minusDays(random.nextInt(1000))
            .format(DateTimeFormatter.ofPattern("yyyyMMdd"))
}

// Main execution
if (args.length != 1) {
    println "Usage: groovy script_name.groovy <number_of_rows>"
    System.exit(1)
}

try {
    int numRows = args[0].toInteger()
    generateCSV("generated_trade.csv", numRows)
} catch (NumberFormatException e) {
    println "Error: Please provide a valid integer for the number of rows."
    System.exit(1)
}