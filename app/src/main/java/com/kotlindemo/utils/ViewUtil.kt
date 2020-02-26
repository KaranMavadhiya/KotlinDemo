package com.kotlindemo.utils

import android.view.View
import android.view.animation.Animation
import android.view.animation.OvershootInterpolator
import android.view.animation.ScaleAnimation


object ViewUtil {

    fun setVisibility(view: View, visibility: Boolean) {
        if (visibility) {
            if (view.visibility == View.GONE) {
                view.visibility = View.VISIBLE
            }
        } else {
            if (view.visibility == View.VISIBLE) {
                view.visibility = View.GONE
            }
        }
    }

    /*
     * @param view : View object for animation
     * @param delay : Delay for view animation
     * @param duration : Duration of animation
     */
    fun animateView( view: View, delay: Long,  duration: Long) {
        view.scaleX = 0.8f
        view.scaleY = 0.8f
        view.animate()
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(duration)
            .setStartDelay(delay)
            .setInterpolator(OvershootInterpolator())
            .start()
    }
}