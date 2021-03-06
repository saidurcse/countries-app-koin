package com.android.countries.ui.details

import com.android.countries.R
import com.android.countries.databinding.FragmentCountryDetailsBinding
import com.android.countries.db.model.CountriesData
import com.android.countries.util.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment

class CountriesDetailsFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(data: CountriesData) = CountriesDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("country_data_row", data)
            }
        }
    }

    private var country: CountriesData? = null
    private lateinit var mViewDataBinding: FragmentCountryDetailsBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        country =  arguments?.getParcelable("country_data_row")
        Log.d(TAG, country?.name.toString())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewDataBinding  = DataBindingUtil.inflate(inflater,
            R.layout.fragment_country_details, container, false)
        val mRootView = mViewDataBinding.root
        mViewDataBinding.lifecycleOwner = this
        return mRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        enableBackButton()
        mViewDataBinding.country = country
        var currency : String = ""
        country?.currency?.forEach {
             currency = "$it "
        }
        mViewDataBinding.currency = currency
    }

    private fun enableBackButton() {
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as? AppCompatActivity)?.supportActionBar?.setHomeButtonEnabled(true)
    }
}