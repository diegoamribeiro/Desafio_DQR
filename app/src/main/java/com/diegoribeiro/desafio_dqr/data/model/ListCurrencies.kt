package com.diegoribeiro.desafiodqr.data.api.model

import com.google.gson.annotations.SerializedName

data class ListCurrencies(
    @SerializedName("currencies")
    val currencies: Map<String, String>,
)