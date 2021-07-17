package com.faskn.app.weatherapp.core

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.faskn.app.weatherapp.R
import com.faskn.app.weatherapp.utils.extensions.hide
import com.faskn.app.weatherapp.utils.extensions.show
import com.squareup.picasso.Picasso

/**
 * Created by Furkan on 2019-10-16
 */

@BindingAdapter("app:visibility")
fun setVisibility(view: View, isVisible: Boolean) {
    if (isVisible) {
        view.show()
    } else {
        view.hide()
    }
}

@BindingAdapter("app:setWeatherIcon")
fun setWeatherIcon(view: ImageView, iconPath: String?) {
    if (iconPath.isNullOrEmpty()) {
        return
    }
    Picasso.get().cancelRequest(view)
    val newPath = iconPath.replace(iconPath, "a$iconPath")
    val imageid =
        view.context.resources.getIdentifier(newPath + "_svg", "drawable", view.context.packageName)
    val imageDrawable = ResourcesCompat.getDrawable(view.context.resources, imageid, null)
    view.setImageDrawable(imageDrawable)
}

@BindingAdapter("app:setErrorView")
fun setErrorView(view: View, viewState: BaseViewState?) {
    if (viewState?.shouldShowErrorMessage() == true) {
        view.show()
    } else {
        view.hide()
    }

    view.setOnClickListener { view.hide() }
}

@BindingAdapter("app:setErrorText")
fun setErrorText(view: TextView, viewState: BaseViewState?) {
    if (viewState?.shouldShowErrorMessage() == true) {
        view.text = viewState.getErrorMessage()
    } else {
        view.text = view.context.getString(R.string.unexpected_exception)
    }
}
