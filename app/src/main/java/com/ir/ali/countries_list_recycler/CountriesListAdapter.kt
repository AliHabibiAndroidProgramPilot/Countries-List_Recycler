package com.ir.ali.countries_list_recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ir.ali.countries_list_recycler.databinding.CountryItemBinding

class CountriesListAdapter(
//    private val contextActivity: Activity,
    private val countries: ArrayList<CountriesData>
) : RecyclerView.Adapter<CountriesListAdapter.ViewHolder>() {
    inner class ViewHolder(
        private val countryItemBinding: CountryItemBinding
    ) : RecyclerView.ViewHolder(countryItemBinding.root) {
        fun setData(countriesData: CountriesData) {
            countryItemBinding.txtCountryName.text = countriesData.countryName
            countryItemBinding.txtCountryContinent.text = countriesData.countryContinent
            countryItemBinding.txtCountryCode.text = countriesData.countryCode
            countryItemBinding.imgCountry.setImageResource(countriesData.countryFlag)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(CountryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(countries[position])
    }

    override fun getItemCount(): Int = countries.size
}