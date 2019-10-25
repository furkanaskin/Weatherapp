package com.faskn.app.weatherapp.ui.splash

import android.graphics.Color
import com.faskn.app.weatherapp.R
import com.faskn.app.weatherapp.core.BaseFragment
import com.faskn.app.weatherapp.databinding.FragmentSplashBinding
import com.mikhaellopez.rxanimation.*
import io.reactivex.disposables.CompositeDisposable

class SplashFragment : BaseFragment<SplashFragmentViewModel, FragmentSplashBinding>(SplashFragmentViewModel::class.java) {

    var disposable = CompositeDisposable()

    override fun getLayoutRes() = R.layout.fragment_splash

    override fun initViewModel() {
        mBinding.viewModel = viewModel
    }

    override fun init() {
        super.init()
        startSplashAnimation()

        mBinding.buttonExplore.setOnClickListener {
            endSplashAnimation()
        }
    }

    private fun startSplashAnimation() {
        disposable.add(
                RxAnimation.sequentially(
                        RxAnimation.together(
                                mBinding.imageViewBottomDrawable.translationY(500f),
                                mBinding.imageViewEllipse.fadeOut(0L),
                                mBinding.imageViewBottomDrawable.fadeOut(0L),
                                mBinding.imageViewBigCloud.translationX(-500F, 0L),
                                mBinding.imageViewSmallCloud.translationX(500f, 0L),
                                mBinding.imageViewBottomDrawableShadow.translationY(500f),
                                mBinding.imageViewMainCloud.fadeOut(0L),
                                mBinding.buttonExplore.fadeOut(0L),
                                mBinding.imageViewBottomDrawableShadow.fadeOut(0L)
                        ),

                        RxAnimation.together(
                                mBinding.imageViewBottomDrawable.fadeIn(1000L),
                                mBinding.imageViewBottomDrawable.translationY(-1f),
                                mBinding.imageViewBottomDrawableShadow.fadeIn(1250L),
                                mBinding.imageViewBottomDrawableShadow.translationY(-1f)
                        ),

                        RxAnimation.together(
                                mBinding.imageViewEllipse.fadeIn(1000L),
                                mBinding.imageViewEllipse.translationY(-50F, 1000L)
                        ),

                        RxAnimation.together(
                                mBinding.imageViewBigCloud.translationX(-15f, 1000L),
                                mBinding.imageViewSmallCloud.translationX(25f, 1000L)
                        ),

                        mBinding.imageViewMainCloud.fadeIn(500L),
                        mBinding.buttonExplore.fadeIn(1000L)
                )
                        .subscribe()
        )
    }

    private fun endSplashAnimation() {
        disposable.add(
                RxAnimation.sequentially(
                        RxAnimation.together(
                                mBinding.imageViewBottomDrawable.fadeOut(300L),
                                mBinding.imageViewBottomDrawable.translationY(100f),
                                mBinding.imageViewBottomDrawableShadow.fadeOut(300L),
                                mBinding.imageViewBottomDrawableShadow.translationY(100f)
                        ),

                        RxAnimation.together(
                                mBinding.imageViewEllipse.fadeOut(300L),
                                mBinding.imageViewEllipse.translationY(500F, 300L)
                        ),

                        RxAnimation.together(
                                mBinding.imageViewBigCloud.translationX(500f, 300L),
                                mBinding.imageViewSmallCloud.translationX(-500f, 300L)
                        ),

                        mBinding.imageViewMainCloud.fadeOut(300L),
                        mBinding.buttonExplore.fadeOut(300L),
                        mBinding.rootView.backgroundColor(Color.parseColor("#5D50FE"), Color.parseColor("#FFFFFF"), duration = 750L))
                        .doOnTerminate { navigate(R.id.action_splashFragment_to_dashboardFragment) }
                        .subscribe()

        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}
