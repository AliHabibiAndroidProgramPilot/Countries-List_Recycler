package com.ir.ali.countries_list_recycler

import android.app.Activity
import android.content.Intent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ir.ali.countries_list_recycler.databinding.CountryItemBinding

class CountriesListAdapter(
    private val contextActivity: Activity,
    private val countries: ArrayList<CountriesData>
) : RecyclerView.Adapter<CountriesListAdapter.ViewHolder>() {
    inner class ViewHolder(
        val countryItemBinding: CountryItemBinding
    ) : RecyclerView.ViewHolder(countryItemBinding.root) {
        init {
            countryItemBinding.root.setOnClickListener {
                contextActivity.startActivity(
                    Intent(contextActivity, CountryShow::class.java)
                )
            }
        }
        fun setData(countriesData: CountriesData) {
            countryItemBinding.txtCountryName.text = countriesData.countryName
            countryItemBinding.txtCountryContinent.text = countriesData.countryContinent
            countryItemBinding.txtCountryCode.text = countriesData.countryCode
            countryItemBinding.imgCountry.setImageResource(countriesData.countryFlag)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(CountryItemBinding.inflate(
            contextActivity.layoutInflater,
            parent,
            false
        ))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(countries[position])
        holder.countryItemBinding.btnDelete.setOnClickListener {
            val layoutPosition = holder.layoutPosition
            countries.removeAt(layoutPosition)
            notifyItemRemoved(layoutPosition)
        }
    }

    override fun getItemCount(): Int = countries.size
}