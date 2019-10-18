package com.faskn.app.weatherapp.core

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by Furkan on 2019-10-16
 */

open class BaseDiffCallback<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem == newItem

    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}
