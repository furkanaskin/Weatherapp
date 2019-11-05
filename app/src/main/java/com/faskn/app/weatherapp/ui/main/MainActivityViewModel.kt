package com.faskn.app.weatherapp.ui.main

import androidx.databinding.ObservableField
import com.faskn.app.weatherapp.core.BaseViewModel
import javax.inject.Inject

class MainActivityViewModel @Inject internal constructor() : BaseViewModel() {
    var toolbarTitle: ObservableField<String> = ObservableField()
}
