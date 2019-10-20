package org.drulabs.myshelf.utils

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

fun readRawResource(context: Context, fileName: String): String? {
    try {
        val resourceId = context.resources.getIdentifier(fileName, "raw", context.packageName)
        val contentStream = context.resources.openRawResource(resourceId)
        val inputStreamReader = InputStreamReader(contentStream)
        val sb = StringBuilder()
        var line: String?
        val br = BufferedReader(inputStreamReader)
        line = br.readLine()
        while (line != null) {
            sb.append(line)
            line = br.readLine()
        }
        br.close()

        return sb.toString()
    } catch (e: Exception) {
        return null
    }
}