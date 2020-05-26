package com.kotlindemo.ui.splash

import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.kotlindemo.R
import com.kotlindemo.base.BaseActivity
import com.kotlindemo.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : BaseActivity() , Animation.AnimationListener {

    private fun performNavigation() {
        startActivity(MainActivity::class.java)
        finishAffinity()
    }

    private fun executeHandler() {
        val delayMillis: Long = 3000
        /*
         * Handler is used to set some delay on this screen
         */
        Handler().postDelayed({ performNavigation() },delayMillis)
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_splash
    }

    override fun initializeComponents() {
        val loadAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.transition_from_top)
        // set animation listener
        loadAnimation.setAnimationListener(this)
        // start the animation
       imageLogo.startAnimation(loadAnimation)
    }

    override fun onAnimationStart(animation: Animation) {}
    override fun onAnimationEnd(animation: Animation) {
        executeHandler()
    }
    override fun onAnimationRepeat(animation: Animation) {}
}