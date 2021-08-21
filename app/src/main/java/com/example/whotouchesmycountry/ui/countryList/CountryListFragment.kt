package com.example.whotouchesmycountry.ui.countryList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whotouchesmycountry.data.Country
import com.example.whotouchesmycountry.R
import com.example.whotouchesmycountry.ui.countryActivity.CountriesProviderInterface
import kotlinx.android.synthetic.main.country_fragment_list.*
import com.example.whotouchesmycountry.ui.customViews.MarginItemDecoration


class CountryListFragment: Fragment() {

    private var countriesProvider : CountriesProviderInterface? = null
    val  listAdapter = CountryAdapter()
    private var listener : (Country) ->Unit = {}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.country_fragment_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        setupViewModel()
    }

    // setup method

    private fun setupViewModel() {
        countriesProvider?.getListOfCountries?.observe(requireActivity(), {
            listAdapter.updateCountryArray(it)
            listAdapter.notifyDataSetChanged()
        })
    }

    private fun setupRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(activity)
        listAdapter.clickListener = listener
        recyclerView.adapter = listAdapter
        recyclerView.addItemDecoration(
            MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.country_list_cell_margin))
        )
    }

    // setter methods

    fun setListener(listener: (Country) ->Unit){
        this.listener = listener
    }

    fun setViewModel(contentProvider: CountriesProviderInterface) {
        countriesProvider = contentProvider
    }
}
