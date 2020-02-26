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
import kotlinx.android.synthetic.main.layout_registration_header.*

class ForgotPasswordFragment  : BaseFragment(), View.OnClickListener {

    private lateinit var progress: ProgressDialog

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
        editEmail.afterTextChanged(forgotPasswordViewModel::setEmail)

        // Show/Hide loader based on observer Boolean
        forgotPasswordViewModel.isLoading().observe(this, Observer {
            if (it)
                progress = ProgressDialog.showProgressDialog(activity?.supportFragmentManager)
            else
                progress.dismiss()
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

    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }
        })
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
                editEmail.error = getString(R.string.err_please_enter_email_address)
                false
            }
            !CommonUtils.isValidEmailAddress(editEmail.text.toString().trim()) -> {
                editEmail.requestFocus()
                ViewUtil.animateView(inputEmail, 10, 200)
                editEmail.error = getString(R.string.err_please_enter_valid_email_address)
                false
            }
            else -> true
        }
    }
}