package com.faskn.app.weatherapp.utils.extensions

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.textfield.TextInputLayout

fun ViewGroup.inflate(@LayoutRes resourceId: Int) =
    LayoutInflater.from(context).inflate(
        resourceId,
        this,
        false
    )

fun <T : ViewDataBinding?> ViewGroup.bindingInflate(@LayoutRes resourceId: Int) =
    DataBindingUtil.inflate<T>(
        LayoutInflater.from(context),
        resourceId,
        this,
        false
    )

inline fun ViewGroup.forEach(action: (view: View) -> Unit) {
    for (index in 0 until childCount) {
        action(getChildAt(index))
    }
}

fun TextInputLayout.disableHintAnimation() {
    isHintAnimationEnabled = false
}

fun EditText.clearBackground() {
    val paddingBottom = paddingBottom
    val paddingTop = paddingTop
    val paddingLeft = paddingLeft
    val paddingRight = paddingRight
    background = null
    setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
}

fun View.hide() {
    visibility = GONE
}

fun View.show() {
    visibility = VISIBLE
}

fun View.showKeyboard(activity: Activity) {
    val inputManager: InputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun View.hideKeyboard(activity: Activity) {
    val view = activity.findViewById<View>(android.R.id.content)
    val inputManager: InputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(view.windowToken, 0)
}
