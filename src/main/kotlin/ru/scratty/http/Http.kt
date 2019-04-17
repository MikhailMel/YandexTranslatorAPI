package ru.scratty.http

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection

object Http {

    fun post(url: HttpUrl, parameters: Map<String, String> = emptyMap()): Response {
        val connection = url.build().openConnection() as HttpURLConnection
        connection.requestMethod = "POST"

        if (parameters.isNotEmpty()) {
            val sb = StringBuilder()
            parameters.entries.forEach {
                sb.append("${it.key}=${it.value}&")
            }
            sb.removeRange(sb.length - 1, sb.length - 1)

            connection.doOutput = true
            BufferedWriter(OutputStreamWriter(connection.outputStream, "UTF-8")).apply {
                write(sb.toString())
                flush()
            }.close()
        }

        val responseCode = connection.responseCode

        val inputStream = if (responseCode == HttpURLConnection.HTTP_OK) {
            connection.inputStream
        } else {
            connection.errorStream
        }

        val response = StringBuilder()
        BufferedReader(InputStreamReader(inputStream, "UTF-8")).apply {
            lines().forEach {
                response.appendln(it)
            }
        }.close()

        return Response(responseCode, response.toString())
    }
}