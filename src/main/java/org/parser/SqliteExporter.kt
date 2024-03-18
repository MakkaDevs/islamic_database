package org.parser

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.parser.FileUtils.createDirIfNotExist
import java.io.File
import java.sql.DriverManager

fun main() {
    // Create the json directory if it doesn't exist
    createDirIfNotExist("json")
    exportSqlite()
}

private fun exportSqlite(path: String = "sqlite") {
    runBlocking(Dispatchers.IO) {
        // Walk through the directory and filter out the sqlite files
        File(path).walk().filter { it.name != path }.forEach {
            // If the file is sqlite file, create the json and csv directories,
            // to contain tables in json and csv format
            if (it.isFile && it.extension == "sqlite") {
                val jsonOut = it.path.replace(".sqlite", "").replace("sqlite", "json")
                val csvOut = it.path.replace(".sqlite", "").replace("sqlite", "csv")
                createDirIfNotExist(jsonOut)
                createDirIfNotExist(csvOut)

                // Connect to the sqlite database and export the tables to json, concurrently
                launch {
                    DriverManager.getConnection("jdbc:sqlite:${it.path}").use { connection ->
                        DatabaseUtils.exportSqlite(connection, jsonOut)
                    }
                }

            } else if (it.isDirectory) {
                createDirIfNotExist(it.path.replace("sqlite", "json"))
                createDirIfNotExist(it.path.replace("sqlite", "csv"))
            }
        }
    }
}