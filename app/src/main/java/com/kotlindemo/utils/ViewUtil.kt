package com.kotlindemo.utils

import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.view.View
import android.view.ViewConfiguration
import android.view.animation.OvershootInterpolator
import com.kotlindemo.R

object ViewUtil {

    fun <T : View?> findViewByName(context: Context, view: View, viewName: String?): T {
        val id: Int = context.resources.getIdentifier(viewName, "id", context.packageName)
        return view.findViewById<View>(id) as T
    }

    fun <T : View?> findViewByName(context: Context, activity: Activity, viewName: String?): T {
        val id: Int = context.resources.getIdentifier(viewName, "id", context.packageName)
        return activity.findViewById<View>(id) as T
    }

    fun getStatusBarHeight(context: Context): Int {
        val resourceId: Int = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
           return context.resources.getDimensionPixelSize(resourceId)
        }
        return 0
    }

    fun getActionBarHeight(context: Context): Int {
        val actionBarHeight: Int
        val styledAttributes: TypedArray = context.theme.obtainStyledAttributes(intArrayOf(R.attr.actionBarSize))
        actionBarHeight = styledAttributes.getDimension(0, 0f).toInt()
        styledAttributes.recycle()
        return actionBarHeight
    }

    fun getNavigationBarHeight(context: Context): Int {
        val hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey()
        val resourceId: Int = context.resources.getIdentifier("navigation_bar_height", "dimen", "android")
        if (resourceId > 0 && !hasMenuKey) {
            return context.resources.getDimensionPixelSize(resourceId)
        }
        return 0
    }

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