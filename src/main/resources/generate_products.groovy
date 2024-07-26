def fileName = 'generated_products.csv'
def numLines = 100000

new File(fileName).withWriter { writer ->
    writer.writeLine("product_id,product_name")

    (1..numLines).each { id ->
        writer.writeLine("$id,Product $id")
    }
}

println "CSV file '$fileName' with $numLines lines has been generated."