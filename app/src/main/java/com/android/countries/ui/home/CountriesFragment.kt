package com.android.countries.ui.home

import com.android.countries.R
import com.android.countries.databinding.FragmentCountriesBinding
import com.android.countries.db.model.CountriesData
import com.android.countries.util.replaceFragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.countries.MainActivity
import com.android.countries.ui.details.CountriesDetailsFragment
import com.android.countries.ui.CountryClickListener
import kotlinx.android.synthetic.main.fragment_countries.*
import org.koin.android.viewmodel.ext.android.viewModel

class CountriesFragment : Fragment(), CountryClickListener {

    private val countriesViewModel by viewModel<CountriesViewModel>()
    private lateinit var countriesAdapter: CountriesAdapter
    private lateinit var mViewDataBinding: FragmentCountriesBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewDataBinding  = DataBindingUtil.inflate(inflater,
            R.layout.fragment_countries, container, false)
        val mRootView = mViewDataBinding.root
        mViewDataBinding.lifecycleOwner = this
        return mRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setView()
        removeBackButton()
        mViewDataBinding.viewModel = countriesViewModel
        countriesViewModel.getAllCountries()
        countriesViewModel.countriesList.observe(viewLifecycleOwner, Observer {
            Log.d("@@countries", it.size.toString())
            if (it.isNotEmpty() && it != null) {
                countriesAdapter.setCountries(it)
            }
        })

    }

    private fun removeBackButton() {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as? AppCompatActivity)?.supportActionBar?.setHomeButtonEnabled(false)
    }

    private fun setView() {
        countriesAdapter = CountriesAdapter(context, this)
        rv_countries.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv_countries.adapter = countriesAdapter
        rv_countries.isNestedScrollingEnabled = false
    }

    override fun onItemClick(country : CountriesData) {
        (activity as MainActivity).replaceFragment(
            CountriesDetailsFragment.newInstance(country),
            R.id.fragment_container, "countriesdetails")
    }
}
