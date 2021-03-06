package com.diegoribeiro.desafio_dqr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.diegoribeiro.desafio_dqr.data.repository.Repository
import com.diegoribeiro.desafio_dqr.utils.Constants.Companion.CALCULATED_FROM
import com.diegoribeiro.desafio_dqr.utils.Constants.Companion.CALCULATED_RESULT
import com.diegoribeiro.desafio_dqr.utils.Constants.Companion.CALCULATED_TO
import com.diegoribeiro.desafio_dqr.utils.Constants.Companion.CALCULATED_VALUE
import com.diegoribeiro.desafio_dqr.view.MainViewModel
import com.diegoribeiro.desafiodqr.data.api.model.ListCurrencies
import com.diegoribeiro.desafiodqr.data.api.model.ListQuotes
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private var repository = Repository()
    private lateinit var spinner1: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var editText: EditText
    private lateinit var button: Button
    private var stringValue: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    initializeViewModel()

    spinner1 = findViewById(R.id.spinner)
    spinner2 = findViewById(R.id.spinner2)
    editText = findViewById(R.id.editText)
    button = findViewById(R.id.button)

    setupObservers()
    setupListeners()
    }

    private fun setupListeners() {
        button.setOnClickListener {
            viewModel.getQuotesList()
        }
    }

    private fun passData(result: String, fromCurrency: String, toCurrency:String, value: String){
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(CALCULATED_FROM, fromCurrency)
        intent.putExtra(CALCULATED_TO, toCurrency)
        intent.putExtra(CALCULATED_RESULT, result)
        intent.putExtra(CALCULATED_VALUE, value)
        startActivity(intent)

    }

    private fun callConvertCurrency(listQuotes: ListQuotes){
        val value = editText.text.toString()
        val validation = viewModel.verifyDataFromUser(value)
        if (validation){
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
        }else{
            val convertFrom = spinner1.selectedItem.toString()
            val convertTo = spinner2.selectedItem.toString()
            val convertedValue = viewModel.convertCurrency(convertFrom, convertTo, value.toDouble(), listQuotes)
            val df = DecimalFormat("#.##")
            stringValue = "$value $convertFrom to $convertTo = "+ df.format(convertedValue)
            passData(convertedValue.toString(), fromCurrency = convertFrom, toCurrency = convertTo, value= value)
        }
    }

    private fun initializeViewModel(){
        viewModel = ViewModelProvider(
            this,
            MainViewModel.MainViewModelFactory(repository)
        ).get(MainViewModel::class.java)
    }

    private fun populateSpinners(list: ListCurrencies){
        val arrayList = arrayListOf<String>()

        list.currencies.forEach {
            val spinnerEntry = "${it.key} - ${it.value}"
            arrayList.add(spinnerEntry)
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = adapter
        spinner2.adapter = adapter
    }

    private fun setupObservers(){

        viewModel.listQuotesLiveData.observe(this, Observer{
            callConvertCurrency(it)
        })

        viewModel.listCurrenciesLiveData.observe(this, Observer {
            populateSpinners(it)

        })
    }
}


