package org.parser

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import java.io.File
import java.sql.Connection

fun exportTableToJson(connection: Connection, table: String, outputDir: String) {
    try {
        connection.createStatement().use { statement ->
            val resultSet = statement.executeQuery("SELECT * FROM $table")
            val rows: MutableList<Map<String?, Any?>> =
                ArrayList()
            // Get column names
            val columnCount = resultSet.metaData.columnCount
            val columnNames = arrayOfNulls<String>(columnCount)

            for (i in 1..columnCount) {
                columnNames[i - 1] = resultSet.metaData.getColumnName(i)
            }
            // Iterate through the result set and add rows to the list
            while (resultSet.next()) {
                val row: MutableMap<String?, Any?> =
                    LinkedHashMap()
                for (columnName in columnNames) {
                    val columnValue = resultSet.getObject(columnName)
                    row[columnName] = columnValue
                }
                rows.add(row)
            }

            // Create a File to store the JSON
            val jsonFile: File = File("$outputDir/$table.json")

            val mapper = ObjectMapper()
            mapper.enable(SerializationFeature.INDENT_OUTPUT)
            mapper.writeValue(jsonFile, rows)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}