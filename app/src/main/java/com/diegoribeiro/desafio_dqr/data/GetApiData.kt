package com.diegoribeiro.desafiodqr.data.api

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

interface GetApiData {

//    suspend fun getQuotesWithoutRetrofit():String{
//        var inline = ""
//        lateinit var scanner: Scanner
//        CoroutineScope(IO).launch {
//            val url =
//                URL("${BASE_URL}/live?access_key=${API_KEY}")
//            val conn = url.openConnection() as HttpURLConnection
//            conn.requestMethod = "GET"
//            scanner = Scanner(conn.url.openStream())
//            while (scanner.hasNext()) {
//                inline += scanner.nextLine()
//            }
//            scanner.close()
//        }
//        return inline
//    }

    companion object {

        var getApiService: GetApiData? = null

        fun getInstance(): GetApiData {

            if (getApiService == null) {
                val retrofit = ApiClientInstance.retrofitInstance
                getApiService = retrofit?.create(GetApiData::class.java)
            }
            return getApiService!!
        }
 }

}