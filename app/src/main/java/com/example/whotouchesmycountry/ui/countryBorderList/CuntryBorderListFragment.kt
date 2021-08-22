package com.example.whotouchesmycountry.ui.countryBorderList

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.whotouchesmycountry.R
import com.example.whotouchesmycountry.ui.countryActivity.CountriesProviderInterface
import com.example.whotouchesmycountry.ui.countryList.CountryListFragment
import kotlinx.android.synthetic.main.fragment_country_border_list.*


class CountryBorderListFragment : Fragment() {

    private var countriesProvider: CountriesProviderInterface? = null
    private var listFragment = CountryListFragment()

    // overridden methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_border_list, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadFragment(listFragment)
        setupViewModel()
        handleEmptyArray()
    }

    // setup methods

    private fun setupViewModel() {
        countriesProvider?.getListOfCountries?.observe(requireActivity(),{
            handleEmptyArray()
            listFragment.listAdapter.updateCountryArray(it)
            listFragment.listAdapter.notifyDataSetChanged()
        })
    }

    private fun handleEmptyArray() {
        if (listFragment.listAdapter.isDataNullOrEmpty()) {
            emptyTextView.visibility = View.GONE
        }
    }

    private fun loadFragment(fragment: Fragment) {
        childFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentView,fragment)
            .commit()
    }

    // setters methods

    fun setViewModel(contentProvider: CountriesProviderInterface) {
        countriesProvider = contentProvider
    }
}