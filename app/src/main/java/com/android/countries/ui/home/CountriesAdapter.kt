package com.android.countries.ui.home

import com.android.countries.R
import com.android.countries.databinding.CountriesRowBinding
import com.android.countries.db.model.CountriesData
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.countries.ui.CountryClickListener

class CountriesAdapter(val context: Context?, val clickListener: CountryClickListener) :
    RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>() {

    var countriesList: List<CountriesData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val viewBinding: CountriesRowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.countries_row, parent, false)
        return CountriesViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return countriesList.size
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun setCountries(countries: List<CountriesData>) {
        this.countriesList = countries
        notifyDataSetChanged()
    }

    inner class CountriesViewHolder(private val viewBinding: CountriesRowBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun onBind(position: Int) {
            val row = countriesList[position]
            viewBinding.countries = row
            viewBinding.countryClickInterface = clickListener
        }
    }
}


