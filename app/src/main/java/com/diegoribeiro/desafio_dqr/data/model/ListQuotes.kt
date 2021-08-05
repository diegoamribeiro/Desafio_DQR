package com.diegoribeiro.desafiodqr.data.api.model

import com.google.gson.annotations.SerializedName

data class ListQuotes(
    @SerializedName("quotes")
    val quotes: Map<String, Double>
    )