package com.diegoribeiro.desafio_dqr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.diegoribeiro.desafio_dqr.utils.Constants.Companion.CALCULATED_VALUE

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val intent = intent
        var value = intent.getStringExtra(CALCULATED_VALUE)
        val result = findViewById<TextView>(R.id.tvResult)
        result.text = value.toString()
        Log.d("ResultActivity", value.toString())
    }
}