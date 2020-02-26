package com.kotlindemo.ui.splash

import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.kotlindemo.R
import com.kotlindemo.base.BaseActivity
import com.kotlindemo.ui.main.MainActivity
import com.kotlindemo.utils.Constants
import com.kotlindemo.utils.preferences.PreferenceConstant
import com.kotlindemo.utils.preferences.getBoolean
import com.kotlindemo.utils.preferences.putBoolean
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : BaseActivity() , Animation.AnimationListener {

    private val mRunnable = Runnable { this.performNavigation() }

    private fun performNavigation() {
        if (PreferenceConstant.isUserLogin.getBoolean()!!)
            startActivity(MainActivity::class.java)
        else
            startActivity(Constants.LOGIN_ACTIVITY)
        finish()
    }

    private fun executeHandler() {
        val delayMillis: Long = 2000
        /*
         * Handler is used to set some delay on this screen
         */
        val handler = Handler()
        handler.postDelayed(mRunnable, delayMillis)
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