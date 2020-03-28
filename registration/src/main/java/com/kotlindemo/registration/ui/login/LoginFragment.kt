package com.kotlindemo.registration.ui.login

import android.text.TextUtils
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.kotlindemo.base.BaseFragment
import com.kotlindemo.registration.R
import com.kotlindemo.utils.CommonUtils
import com.kotlindemo.utils.LogUtil
import com.kotlindemo.utils.ViewUtil
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.layout_registration_header.*


class LoginFragment  : BaseFragment(), View.OnClickListener {

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
        updateData()

        setObserver()
    }

    private fun setObserver() {
        // Show/Hide loader based on observer Boolean
        loginViewModel.isLoading().observe(this, Observer {
            if (it)
                showProgressDialog(activity?.supportFragmentManager)
            else
                dismissProgressDialog()
        })

        loginViewModel.getUserData().observe(viewLifecycleOwner, Observer {

            it?.userId?.let { userId -> LogUtil.d("User userId: ", userId) }
            it?.name?.let { name -> LogUtil.d("User name: ", name) }
            it?.email?.let { email -> LogUtil.d("User email: ", email) }
            LogUtil.d("User mobileNumber: ",""+ (it?.mobileNumber ?: ""))

        })
    }

    private fun updateData() {
        editEmail.afterTextChanged {
            inputEmail.isErrorEnabled = false
            loginViewModel.setEmail(it)
        }
        editPassword.afterTextChanged {
            inputPassword.isErrorEnabled = false
            loginViewModel.setPassword(it)
        }
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
                inputEmail.error = getString(R.string.err_please_enter_email_address)
                return false
            }
            !CommonUtils.isValidEmailAddress(editEmail.text.toString().trim()) -> {
                editEmail.requestFocus()
                ViewUtil.animateView(inputEmail,10,200)
                inputEmail.error = getString(R.string.err_please_enter_valid_email_address)
                return false
            }
            TextUtils.isEmpty(editPassword.text.toString().trim()) -> {
                editPassword.requestFocus()
                ViewUtil.animateView(inputPassword,10,200)
                inputPassword.error = getString(R.string.err_please_enter_password)
                return false
            }
            else -> return true
        }
    }
}