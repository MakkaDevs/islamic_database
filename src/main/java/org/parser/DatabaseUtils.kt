package org.parser

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.Connection
import java.sql.SQLException

object DatabaseUtils {
    fun exportSqlite(connection: Connection, jsonOut: String) {
        val tables = getTables(connection)
        runBlocking(Dispatchers.IO) {
            tables.forEach {
                launch {
                    exportTableToJson(connection, it, jsonOut)
                }
            }
        }
    }


    private fun getTables(connection: Connection): List<String> {
        val tables: MutableList<String> = ArrayList()
        try {
            connection.createStatement().use { statement ->
                val resultSet = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table'")
                while (resultSet.next()) {
                    tables.add(resultSet.getString(1))
                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return tables.filter { table ->
            table !in TableExclusion.entries.map { it.table }
        }
    }
}

enum class TableExclusion(val table: String) {
    SQLITE_SEQUENCE("sqlite_sequence"),
    ANDROID_METADATA("android_metadata"),
    ROOM_MASTER_TABLE("room_master_table"),
    ROOM_TABLE_INFO("room_table_info"),
    Z_METADATA("Z_METADATA"),
}