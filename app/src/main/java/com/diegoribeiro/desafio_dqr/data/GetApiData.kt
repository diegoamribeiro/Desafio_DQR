package com.diegoribeiro.desafiodqr.data.api

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

interface GetApiData {


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