package com.example.whotouchesmycountry.ui.countryList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.whotouchesmycountry.R
import com.example.whotouchesmycountry.data.Country
import kotlinx.android.synthetic.main.country_cell.view.*

class CountryViewHolder(val view: View): RecyclerView.ViewHolder(view)

class CountryAdapter( var clickListener: (Country) -> Unit = {}): RecyclerView.Adapter<CountryViewHolder>() {

    private var countryArr = arrayOf<Country>()

    fun updateCountryArray(countryArr: Array<Country>) {
        this.countryArr = countryArr
    }

    fun isDataNullOrEmpty(): Boolean {
        return countryArr.isNullOrEmpty()
    }

    override fun getItemCount(): Int {
        return countryArr.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.country_cell, parent, false)
        return CountryViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.name.text       = buildTextWith(holder.view.context.getString(R.string.name_prefix),        countryArr[position].name)
        holder.view.nativeName.text = buildTextWith(holder.view.context.getString(R.string.native_name_prefix), countryArr[position].nativeName)
        holder.view.setOnClickListener{clickListener(countryArr[position])}
    }

    // helper func
    private fun buildTextWith(prefix: String, name: String): String {
        return "$prefix $name"
    }
}

