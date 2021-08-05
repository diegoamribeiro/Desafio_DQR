package com.diegoribeiro.desafio_dqr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.diegoribeiro.desafio_dqr.utils.Constants.Companion.CALCULATED_FROM
import com.diegoribeiro.desafio_dqr.utils.Constants.Companion.CALCULATED_RESULT
import com.diegoribeiro.desafio_dqr.utils.Constants.Companion.CALCULATED_TO
import com.diegoribeiro.desafio_dqr.utils.Constants.Companion.CALCULATED_VALUE

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val intent = intent
        var fromCurrency = intent.getStringExtra(CALCULATED_FROM)
        var toCurrency = intent.getStringExtra(CALCULATED_TO)
        var resultado = intent.getStringExtra(CALCULATED_VALUE)
        var value = intent.getStringExtra(CALCULATED_RESULT)

        val currencyFrom = findViewById<TextView>(R.id.tvResultFrom)
        val currencyTo = findViewById<TextView>(R.id.tvResultTo)
        val result = findViewById<TextView>(R.id.tvResultResult)
        val valor = findViewById<TextView>(R.id.tvResultValue)

        currencyFrom.text = fromCurrency.toString()
        currencyTo.text = toCurrency.toString()
        result.text = resultado.toString()
        valor.text = value.toString()
        Log.d("ResultActivity", value.toString())
    }
}