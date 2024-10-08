package com.ir.ali.countries_list_recycler

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.ir.ali.countries_list_recycler.databinding.CountryItemBinding

class CountriesListAdapter(
    private val contextActivity: Activity,
    private val countries: ArrayList<CountriesData>
) : RecyclerView.Adapter<CountriesListAdapter.ViewHolder>(), Filterable {
    private val countriesListFull = ArrayList<CountriesData>(countries)

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
        ViewHolder(
            CountryItemBinding.inflate(
                contextActivity.layoutInflater,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(countries[position])
    }

    override fun getItemCount(): Int = countries.size

    override fun getFilter(): Filter =
        object : Filter() {
            override fun performFiltering(searchQuery: CharSequence?): FilterResults {
                val filterResults = ArrayList<CountriesData>()
                if (searchQuery == null || searchQuery.isEmpty()) {
                    filterResults.addAll(countriesListFull)
                } else {
                    val userSearchQuery = searchQuery.toString().trim()
                    filterResults.addAll(
                        countriesListFull.filter { item ->
                            item.countryName.contains(
                                userSearchQuery, true
                            ) || item.countryContinent.contains(
                                userSearchQuery, true)
                        })
                }
                val finalResult = FilterResults()
                finalResult.values = filterResults
                return finalResult
            }
            @SuppressLint("NotifyDataSetChanged")
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(p0: CharSequence?, searchedList: FilterResults?) {
                countries.clear()
                countries.addAll(searchedList?.values as ArrayList<CountriesData>)
                notifyDataSetChanged()
            }
        }

    fun swipeRemove(position: Int) {
        countries.removeAt(position)
        countriesListFull.removeAt(position)
        notifyItemRemoved(position)
    }
    fun reOrderItems(fromPosition: Int, toPosition: Int) {
        val movedItem = countriesListFull[fromPosition]
        countries.removeAt(fromPosition)
        countriesListFull.removeAt(fromPosition)
        countries.add(toPosition, movedItem)
        countriesListFull.add(toPosition, movedItem)
        notifyItemMoved(fromPosition, toPosition)
    }
}