package com.example.whotouchesmycountry.ui.countryActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.whotouchesmycountry.R
import com.example.whotouchesmycountry.data.Country
import com.example.whotouchesmycountry.ui.countryList.CountryListFragment

class CountryListActivity : AppCompatActivity() {

    private var countryListVM: CountryListVM? = null
    private lateinit var listFragment : CountryListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupSelf()
        setupListFragment()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sort_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        countryListVM?.sortCountries(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    // setup func

    private fun setupSelf() {
        countryListVM = ViewModelProviders.of(this)[CountryListVM::class.java]
        title = getString(R.string.country_list_activity_title)
    }

    private fun setupListFragment() {
        listFragment = CountryListFragment()
        countryListVM?.let { listFragment.setViewModel(it) }
        loadFragment(listFragment)
        listFragment.setListener{ country -> openCountryBorderFragmentFor(country) }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentView,fragment)
            .commit()
    }

    private fun openCountryBorderFragmentFor(country: Country) {
        val borderListFragment = countryListVM?.buildCountryBorderFragmentFor(country)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,R.anim.enter_from_left, R.anim.exit_to_right)
            .addToBackStack(null)
        borderListFragment?.let { transaction.replace(R.id.fragmentView, it).commit() }
    }
}