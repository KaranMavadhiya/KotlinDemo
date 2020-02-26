package com.kotlindemo.ui.main

import android.view.Menu
import android.view.MenuItem
import com.kotlindemo.R
import com.kotlindemo.base.BaseActivity
import com.kotlindemo.utils.Constants
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_header.*

class MainActivity : BaseActivity() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.actionLogin) {
            startActivity(Constants.LOGIN_ACTIVITY)
            return true
        } else super.onOptionsItemSelected(item)
    }

    override fun initializeComponents() {
        // set screen Title
        headerTextTitle.text = getString(R.string.screen_main)

        // set toolBar as SupportActionBar and hide Title
        setSupportActionBar(layoutHeader.findViewById(R.id.toolBar))
        supportActionBar?.setDisplayShowTitleEnabled(false)

        addFragment(MainFragment.newInstance(),R.id.frame_container)
    }
}
