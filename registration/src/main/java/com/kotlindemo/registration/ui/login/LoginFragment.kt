package com.kotlindemo.registration.ui.login

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.kotlindemo.base.BaseFragment
import com.kotlindemo.base.ProgressDialog
import com.kotlindemo.registration.R
import com.kotlindemo.utils.CommonUtils
import com.kotlindemo.utils.ViewUtil
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.layout_registration_header.*


class LoginFragment  : BaseFragment(), View.OnClickListener {

    private lateinit var progress: ProgressDialog

    private lateinit var loginViewModel: LoginViewModel

    override fun getLayoutRes(): Int {
        return R.layout.fragment_login
    }

    override fun initializeComponent(view: View?) {
        // set screen Title
        headerTextTitle.text = getString(R.string.screen_login)

        setupUI()

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        // Update data to viewModel
        editEmail.afterTextChanged(loginViewModel::setEmail)
        editPassword.afterTextChanged(loginViewModel::setPassword)


        // Show/Hide loader based on observer Boolean
        loginViewModel.isLoading().observe(this, Observer {
            if (it)
                progress = ProgressDialog.showProgressDialog(activity?.supportFragmentManager)
            else
                progress.dismiss()
        })
    }

    private fun setupUI() {
        ViewUtil.animateView(imageLogo,300,500)
        ViewUtil.animateView(inputEmail,300,500)
        ViewUtil.animateView(inputPassword,300,500)
        ViewUtil.animateView(textForgotPassword,500,500)
        ViewUtil.animateView(buttonSubmit,500,500)
        ViewUtil.animateView(textSignUp,500,500)

        //set spannable text
        CommonUtils.applySpanPrimaryColor(textSignUp,getString(R.string.str_don_t_have_an_account),getString(R.string.str_sign_up))

        // set onClick
        headerImageBack.setOnClickListener(this)
        textForgotPassword.setOnClickListener(this)
        textSignUp.setOnClickListener(this)
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

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.headerImageBack -> activity!!.onBackPressed()
            R.id.textForgotPassword -> Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
            R.id.textSignUp -> Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signUpFragment)
            R.id.buttonSubmit ->{
                when{
                    validateData() -> {
                        context?.let { loginViewModel.apiCallLogin(it) }
                    }
                }
            }
        }
    }

    private fun validateData(): Boolean {
        when {
            TextUtils.isEmpty(editEmail.text.toString().trim()) -> {
                editEmail.requestFocus()
                ViewUtil.animateView(inputEmail,10,200)
                editEmail.error = getString(R.string.err_please_enter_email_address)
                return false
            }
            !CommonUtils.isValidEmailAddress(editEmail.text.toString().trim()) -> {
                editEmail.requestFocus()
                ViewUtil.animateView(inputEmail,10,200)
                editEmail.error = getString(R.string.err_please_enter_valid_email_address)
                return false
            }
            TextUtils.isEmpty(editPassword.text.toString().trim()) -> {
                editPassword.requestFocus()
                ViewUtil.animateView(inputPassword,10,200)
                editPassword.error = getString(R.string.err_please_enter_password)
                return false
            }
            !CommonUtils.isValidPassword(editPassword.text.toString().trim()) -> {
                editPassword.requestFocus()
                ViewUtil.animateView(inputPassword,10,200)
                editPassword.error = getString(R.string.err_please_enter_valid_password)
                return false
            }
            else -> return true
        }
    }
}