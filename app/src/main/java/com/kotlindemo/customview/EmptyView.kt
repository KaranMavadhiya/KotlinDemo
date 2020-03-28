package com.kotlindemo.customview

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import com.kotlindemo.R
import java.util.*

/**
 * This custom view is used to display empty view in any screen
 */
class EmptyView(context: Context, attrs: AttributeSet?) :

    LinearLayout(context, attrs) {

    fun setMessage(message: String?) {
        val emptyMessage: AppCompatTextView = findViewById(R.id.empty_view_tv_message)
        emptyMessage.text = message
    }

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER_VERTICAL
        val inflater =  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        Objects.requireNonNull(inflater).inflate(R.layout.layout_empty_view, this, true)
    }
}