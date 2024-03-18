package org.parser

import java.io.File

object FileUtils {
    fun createDirIfNotExist(path: String): Boolean {
        val dir = File(path)
        if (!dir.exists()) {
            return dir.mkdir()
        }
        return false
    }
}