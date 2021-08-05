package com.diegoribeiro.desafio_dqr.data.repository

import com.diegoribeiro.desafio_dqr.utils.Constants.Companion.API_KEY
import com.diegoribeiro.desafio_dqr.utils.Constants.Companion.BASE_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class Repository {

    suspend fun getStringQuotesList(): String{
        var inline = ""
        lateinit var scanner: Scanner
        CoroutineScope(Dispatchers.IO).async {
            val url =
                URL("${BASE_URL}/live?access_key=${API_KEY}")
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            scanner = Scanner(conn.url.openStream())
            while (scanner.hasNext()) {
                inline += scanner.nextLine()
            }
            scanner.close()
        }.await()
        return inline
    }

    suspend fun getStringCurrenciesList(): String{
        var inline = ""
        lateinit var scanner: Scanner
        CoroutineScope(Dispatchers.IO).async {
            val url =
                URL("${BASE_URL}/list?access_key=${API_KEY}")
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            scanner = Scanner(conn.url.openStream())
            while (scanner.hasNext()) {
                inline += scanner.nextLine()
            }
            scanner.close()
        }.await()
        return inline
    }
}