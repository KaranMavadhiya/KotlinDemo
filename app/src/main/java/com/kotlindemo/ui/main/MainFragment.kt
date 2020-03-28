package com.kotlindemo.ui.main

import android.view.View
import android.widget.Toast
import com.kotlindemo.R
import com.kotlindemo.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment  : BaseFragment(), View.OnClickListener {

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_main
    }

    override fun initializeComponent(view: View?) {
        layoutEmptyView.setOnClickListener(this)
        layoutEmptyView.setMessage(getString(R.string.str_no_data))
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.layoutEmptyView -> {
                reloadView()
            }
        }
    }

    private fun reloadView() {
        Toast.makeText(context,"Reload View",Toast.LENGTH_SHORT).show()
    }
}