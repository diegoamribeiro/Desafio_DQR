package com.diegoribeiro.desafio_dqr.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.diegoribeiro.desafio_dqr.data.repository.Repository
import com.diegoribeiro.desafiodqr.data.api.model.ListCurrencies
import com.diegoribeiro.desafiodqr.data.api.model.ListQuotes
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.lang.Exception

class MainViewModel constructor(private val repository: Repository): ViewModel() {

    private lateinit var listQuotes: ListQuotes
    private val _listQuotesLiveData = MutableLiveData<ListQuotes>()
    val listQuotesLiveData: LiveData<ListQuotes>
        get() = _listQuotesLiveData

    private lateinit var listCurrencies: ListCurrencies
    private val _listCurrenciesLiveData = MutableLiveData<ListCurrencies>()
    val listCurrenciesLiveData: LiveData<ListCurrencies>
        get() = _listCurrenciesLiveData

    init {
        _listQuotesLiveData
        _listCurrenciesLiveData
        getCurrenciesList()
    }

    fun verifyDataFromUser(field: String): Boolean{
        return field.isEmpty()
    }

    fun getQuotesList(){
        CoroutineScope(IO).launch {
            try{
                val jsonString = repository.getStringQuotesList()
                listQuotes = convertQuotesListToObject(jsonString)
                _listQuotesLiveData.postValue(listQuotes)
            } catch (e: Exception){
                Log.e("ConnectionError", e.message.toString())
            }
        }
    }

    fun getCurrenciesList(){
        CoroutineScope(IO).launch {
            try{
                //TODO REMOVER SLEEP
                Thread.sleep(2000)
                val jsonString = repository.getStringCurrenciesList()
                listCurrencies = convertCurrencyListToObject(jsonString)
                _listCurrenciesLiveData.postValue(listCurrencies)
            } catch (e: Exception){
                Log.e("ConnectionError", e.message.toString())
            }
        }
    }

    private fun convertQuotesListToObject(json: String): ListQuotes {
        val jsonObj = JSONObject(json)
        val gsonObject = Gson().fromJson(jsonObj.toString(), ListQuotes::class.java)
        return gsonObject
    }

    private fun convertCurrencyListToObject(json: String): ListCurrencies {
        val jsonObj = JSONObject(json)
        val gsonObject = Gson().fromJson(jsonObj.toString(), ListCurrencies::class.java)
        return gsonObject
    }

    private fun convertToDollar(convertFrom: String, value: Double, listQuotes: ListQuotes):Double{
        val currency = "USD${convertFrom.substring(0,3)}"
        val conversionQuote = listQuotes.quotes[currency]
        val converted = value / conversionQuote!!
        return converted
    }

    fun convertCurrency(convertFrom: String, convertTo: String, value: Double, listQuotes: ListQuotes): Double{
        val convertedToDollar = convertToDollar(convertFrom, value, listQuotes)
        val currency = "USD${convertTo.substring(0,3)}"
        val conversionQuote = listQuotes.quotes[currency]
        val converted = convertedToDollar * conversionQuote!!
        return converted
    }

    class MainViewModelFactory constructor(private val repository: Repository):
            ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(this.repository) as T
        }
    }


}