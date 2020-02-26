package com.kotlindemo.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.kotlindemo.R;

import java.util.Objects;


/**
 * This custom view is used to display empty view in any screen
 */
public class EmptyView extends LinearLayout {

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setOrientation( LinearLayout.VERTICAL);
        setGravity( Gravity.CENTER_VERTICAL);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE);
        Objects.requireNonNull( inflater ).inflate( R.layout.layout_empty_view, this, true);
    }
}
