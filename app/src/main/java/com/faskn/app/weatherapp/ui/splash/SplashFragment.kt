package com.faskn.app.weatherapp.ui.splash

import androidx.navigation.fragment.findNavController
import com.faskn.app.weatherapp.R
import com.faskn.app.weatherapp.core.BaseFragment
import com.faskn.app.weatherapp.core.Constants
import com.faskn.app.weatherapp.databinding.FragmentSplashBinding
import com.faskn.app.weatherapp.utils.extensions.hide
import com.faskn.app.weatherapp.utils.extensions.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashFragmentViewModel, FragmentSplashBinding>(
    R.layout.fragment_splash,
    SplashFragmentViewModel::class.java,
) {

    override fun init() {
        super.init()

        if (binding.viewModel?.sharedPreferences?.getString(Constants.Coords.LON, "")
                .isNullOrEmpty()
        ) {
            binding.buttonExplore.show()
            binding.viewModel?.navigateDashboard = false
        } else {
            binding.buttonExplore.hide()
            binding.viewModel?.navigateDashboard = true
        }

        binding.viewModel?.navigateDashboard?.let { startSplashAnimation(it) }

        binding.buttonExplore.setOnClickListener {
            binding.viewModel?.navigateDashboard?.let { it1 -> endSplashAnimation(it1) }
        }

        binding.rootView.setOnClickListener {
            binding.viewModel?.navigateDashboard?.let { it1 -> endSplashAnimation(it1) }
        }
    }

    private fun startSplashAnimation(navigateToDashboard: Boolean) {
        // TODO: Re-write with compose

        findNavController().graph.setStartDestination(R.id.dashboardFragment) // Little bit tricky solution :)
        if (navigateToDashboard) {
            endSplashAnimation(navigateToDashboard)
        }
    }

    private fun endSplashAnimation(navigateToDashboard: Boolean) {
        // TODO: Re-write with compose

        findNavController().graph.setStartDestination(R.id.dashboardFragment) // Little bit tricky solution :)
        if (navigateToDashboard) {
            navigate(R.id.action_splashFragment_to_dashboardFragment)
        } else {
            navigate(R.id.action_splashFragment_to_searchFragment)
        }
    }
}
