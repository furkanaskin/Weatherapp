package com.faskn.app.weatherapp.ui.splash

import android.content.Intent
import android.os.Bundle
import com.faskn.app.weatherapp.R
import com.faskn.app.weatherapp.core.BaseActivity
import com.faskn.app.weatherapp.databinding.ActivitySplashBinding
import com.faskn.app.weatherapp.ui.main.MainActivity
import com.mikhaellopez.rxanimation.*
import com.uber.autodispose.autoDisposable

class SplashActivity :
    BaseActivity<SplashActivityViewModel, ActivitySplashBinding>(SplashActivityViewModel::class.java) {

    override fun getLayoutRes() = R.layout.activity_splash

    override fun initViewModel(viewModel: SplashActivityViewModel) {
        binding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startSplashAnimation()

        binding.buttonExplore.setOnClickListener {
            val navigationIntent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(navigationIntent)
            finish()
        }
    }

    private fun startSplashAnimation() {
        RxAnimation.sequentially(
            RxAnimation.together(
                binding.imageViewBottomDrawable.translationY(500f),
                binding.imageViewEllipse.fadeOut(0L),
                binding.imageViewBottomDrawable.fadeOut(0L),
                binding.imageViewBigCloud.translationX(-500F, 0L),
                binding.imageViewSmallCloud.translationX(500f, 0L),
                binding.imageViewBottomDrawableShadow.translationY(500f),
                binding.imageViewMainCloud.fadeOut(0L),
                binding.buttonExplore.fadeOut(0L),
                binding.imageViewBottomDrawableShadow.fadeOut(0L)
            ),

            RxAnimation.together(
                binding.imageViewBottomDrawable.fadeIn(1000L),
                binding.imageViewBottomDrawable.translationY(-1f),
                binding.imageViewBottomDrawableShadow.fadeIn(1250L),
                binding.imageViewBottomDrawableShadow.translationY(-1f)
            ),

            RxAnimation.together(
                binding.imageViewEllipse.fadeIn(1000L),
                binding.imageViewEllipse.translationY(-50F, 1000L)
            ),

            RxAnimation.together(
                binding.imageViewBigCloud.translationX(-15f, 1000L),
                binding.imageViewSmallCloud.translationX(25f, 1000L)
            ),

            binding.imageViewMainCloud.fadeIn(500L),
            binding.buttonExplore.fadeIn(1000L)
        )
            .autoDisposable(scopeProvider)
            .subscribe()
    }
}
