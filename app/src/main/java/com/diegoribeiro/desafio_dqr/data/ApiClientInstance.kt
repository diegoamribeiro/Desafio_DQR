package com.diegoribeiro.desafiodqr.data.api

import com.diegoribeiro.desafio_dqr.utils.Constants.Companion.BASE_URL

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClientInstance {

    var retrofit: Retrofit? = null

    //create a retrofit instance
    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

}