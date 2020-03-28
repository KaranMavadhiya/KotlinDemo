package com.kotlindemo.registration.ui.forgot

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kotlindemo.base.BaseFragment
import com.kotlindemo.base.ProgressDialog
import com.kotlindemo.registration.R
import com.kotlindemo.utils.CommonUtils
import com.kotlindemo.utils.ViewUtil
import kotlinx.android.synthetic.main.fragment_forgot_password.*
import kotlinx.android.synthetic.main.fragment_forgot_password.buttonSubmit
import kotlinx.android.synthetic.main.fragment_forgot_password.editEmail
import kotlinx.android.synthetic.main.fragment_forgot_password.imageLogo
import kotlinx.android.synthetic.main.fragment_forgot_password.inputEmail
import kotlinx.android.synthetic.main.fragment_signup.*
import kotlinx.android.synthetic.main.layout_registration_header.*

class ForgotPasswordFragment  : BaseFragment(), View.OnClickListener {

    private lateinit var forgotPasswordViewModel: ForgotPasswordViewModel

    override fun getLayoutRes(): Int {
      return R.layout.fragment_forgot_password
    }

    override fun initializeComponent(view: View?) {
        // set screen Title
        headerTextTitle.text = getString(R.string.screen_forgot_password)

        setupUI()

        forgotPasswordViewModel = ViewModelProvider(this).get(ForgotPasswordViewModel::class.java)

        // Update data to viewModel
        editEmail.afterTextChanged {
            inputEmail.isErrorEnabled = false
            forgotPasswordViewModel.setEmail(it)
        }

        // Show/Hide loader based on observer Boolean
        forgotPasswordViewModel.isLoading().observe(this, Observer {
            if (it)
               showProgressDialog(activity?.supportFragmentManager)
            else
               dismissProgressDialog()
        })
    }

    private fun setupUI() {
        ViewUtil.animateView(imageLogo,300,500)
        ViewUtil.animateView(inputEmail,300,500)
        ViewUtil.animateView(buttonSubmit,500,500)

        // set onClick
        headerImageBack.setOnClickListener(this)
        buttonSubmit.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.headerImageBack -> activity!!.onBackPressed()
            R.id.buttonSubmit ->{
                when{
                    validateData() -> {
                        context?.let { forgotPasswordViewModel.apiCallForgotPassword(it) }
                    }
                }
            }
        }
    }

    private fun validateData(): Boolean {
        return when {
            TextUtils.isEmpty(editEmail.text.toString().trim()) -> {
                editEmail.requestFocus()
                ViewUtil.animateView(inputEmail, 10, 200)
                inputEmail.error = getString(R.string.err_please_enter_email_address)
                false
            }
            !CommonUtils.isValidEmailAddress(editEmail.text.toString().trim()) -> {
                editEmail.requestFocus()
                ViewUtil.animateView(inputEmail, 10, 200)
                inputEmail.error = getString(R.string.err_please_enter_valid_email_address)
                false
            }
            else -> true
        }
    }
}