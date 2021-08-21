package com.example.whotouchesmycountry.ui.countryActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.whotouchesmycountry.data.Country
import com.example.whotouchesmycountry.R
import com.example.whotouchesmycountry.data.*
import com.example.whotouchesmycountry.networking.RequestManager
import com.example.whotouchesmycountry.ui.countryBorderList.CountryBorderListFragment

interface CountriesProviderInterface {
    val getListOfCountries : LiveData<Array<Country>>
}

class CountryListVM : ViewModel(), CountriesProviderInterface {

    private val requestManager = RequestManager()
    private var originalCountryArray = MutableLiveData<Array<Country>>()
    private var countryArray = MutableLiveData<Array<Country>>()


    override val getListOfCountries : LiveData<Array<Country>>
        get() = countryArray

    init {
        getCountries()
    }



    private fun getCountries() {
        requestManager.makeRequest {
            when(val result = it) {
                is Success -> {
                    result.value.let { countries ->
                        originalCountryArray.postValue(countries)
                        countryArray.postValue(countries)
                    }
                    println("qwe is Success")

                }
                is Failure -> {
                    println(result.reason)
                    println("qwe is Failure")
                }
            }
        }
    }

    fun sortCountries(id: Int) {
        when (id) {
            R.id.none -> {
                countryArray = originalCountryArray
            }
            R.id.name_up -> {
                setSortedArray(originalCountryArray.value?.sortedBy { it.name })
            }
            R.id.name_down -> {
                setSortedArray(originalCountryArray.value?.sortedByDescending { it.name })
            }
            R.id.size_up -> {
                setSortedArray(originalCountryArray.value?.sortedBy { it.area })
            }
            R.id.size_down -> {
                setSortedArray(originalCountryArray.value?.sortedByDescending { it.name })
            }
        }

    }

    fun buildCountryBorderFragmentFor(country: Country): CountryBorderListFragment {
        val vm = CountryBorderListVM(country, originalCountryArray.value!!) // cant be null
        val fragment = CountryBorderListFragment()
        fragment.setViewModel(vm)
        return fragment
    }

    private fun setSortedArray(array: List<Country>?) {
        array?.let { countryArray.postValue(it.toTypedArray()) }
    }



}