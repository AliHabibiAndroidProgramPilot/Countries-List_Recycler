package com.ir.ali.countries_list_recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ir.ali.countries_list_recycler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CountriesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val countries: ArrayList<CountriesData> =
            arrayListOf(
                CountriesData("USA", "North America", "+1", R.drawable.usa_flag),
                CountriesData("Argentina", "South America", "+54", R.drawable.argentina_falg),
                CountriesData("Mexico", "North America", "+52", R.drawable.mexcio_flag),
                CountriesData("Canada", "North America", "+1", R.drawable.canada_flag),
                CountriesData("Brazil", "South America", "+55", R.drawable.brazile_flag),
                CountriesData("Finland", "Europe", "+358", R.drawable.finland_flag),
                CountriesData("Russia", "Asia", "+7", R.drawable.russia_falg),
                CountriesData("France", "Europe", "+33", R.drawable.france_flag),
                CountriesData("China", "Asia", "+86", R.drawable.china_flag),
                CountriesData("Germany", "Europe", "+49", R.drawable.germany_flag),
                CountriesData("United Kingdom", "Europe", "+44", R.drawable.uk_flag),
                CountriesData("Philippines", "Asia", "+63", R.drawable.philippine_flag),
                CountriesData("Japan", "Asia", "+81", R.drawable.japan_flag),
                CountriesData("Spain", "Europe", "+34", R.drawable.spain_flag),
            )

        adapter = CountriesListAdapter(this, countries)
        binding.RecyclerView.adapter = adapter
        binding.RecyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                adapter.reOrderItems(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.swipeRemove(viewHolder.layoutPosition)
            }
        }).attachToRecyclerView(binding.RecyclerView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val item = menu?.findItem(R.id.search_item)
        val searchView: SearchView? = item?.actionView as SearchView?
        searchView?.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(searchQuery: String?): Boolean {
                adapter.filter.filter(searchQuery)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}