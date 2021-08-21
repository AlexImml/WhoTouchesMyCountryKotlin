package com.example.whotouchesmycountry.ui.countryActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.whotouchesmycountry.data.Country
import kotlin.concurrent.thread


class CountryBorderListVM(selectedCountry: Country, countriesArray: Array<Country>) : CountriesProviderInterface {

    private var countryArray = MutableLiveData<Array<Country>>()
    override val getListOfCountries : LiveData<Array<Country>>
        get() = countryArray


    init {
        filterCountriesFor(selectedCountry, countriesArray)
    }

    private fun filterCountriesFor(selectedCountry: Country, countriesArray: Array<Country>) {
        thread {
            val filteredCountries = mutableListOf<Country>()
            val selectedCountryBorders = selectedCountry.borders.toMutableSet()
            for  (country in countriesArray) {
                if (selectedCountryBorders.contains(country.cioc)) {
                    filteredCountries.add(country)
                    selectedCountryBorders.remove(country.cioc)
                    if (selectedCountryBorders.isEmpty()) break
                }
            }
            countryArray.postValue(filteredCountries.toTypedArray())
        }
    }

}