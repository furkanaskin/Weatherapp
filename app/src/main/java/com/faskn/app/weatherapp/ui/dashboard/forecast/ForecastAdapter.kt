package com.faskn.app.weatherapp.ui.dashboard.forecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.faskn.app.weatherapp.R
import com.faskn.app.weatherapp.core.BaseAdapter
import com.faskn.app.weatherapp.databinding.ItemForecastBinding
import com.faskn.app.weatherapp.domain.model.ListItem

/**
 * Created by Furkan on 2019-10-25
 */

class ForecastAdapter(private val callBack: (ListItem, View) -> Unit) : BaseAdapter<ListItem>(diffCallback) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val mBinding = DataBindingUtil.inflate<ItemForecastBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_forecast,
            parent,
            false
        )
        val viewModel = ForecastItemViewModel()
        mBinding.viewModel = viewModel

        mBinding.rootView.setOnClickListener {
            mBinding.viewModel?.item?.get()?.let {
                ViewCompat.setTransitionName(mBinding.cardView, "weatherItem")
                callBack.invoke(it, mBinding.cardView)
            }
        }
        return mBinding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as ItemForecastBinding).viewModel?.item?.set(getItem(position))
        binding.executePendingBindings()
    }
}

val diffCallback = object : DiffUtil.ItemCallback<ListItem>() {
    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem.dtTxt == newItem.dtTxt
}
