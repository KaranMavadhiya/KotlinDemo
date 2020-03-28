package com.kotlindemo.registration.ui.signup

import android.text.SpannableString
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kotlindemo.base.BaseFragment
import com.kotlindemo.registration.R
import com.kotlindemo.utils.*
import kotlinx.android.synthetic.main.fragment_signup.*
import kotlinx.android.synthetic.main.layout_registration_header.*

class SignupFragment  : BaseFragment(), View.OnClickListener {

    private lateinit var signupViewModel: SignupViewModel

    override fun getLayoutRes(): Int {
        return R.layout.fragment_signup
    }

    override fun initializeComponent(view: View?) {
        // set screen Title
        headerTextTitle.text = getString(R.string.screen_registration)

        setupUI()

        signupViewModel = ViewModelProvider(this).get(SignupViewModel::class.java)

        // Update data to viewModel
        updateData()


        // Show/Hide loader based on observer Boolean
        signupViewModel.isLoading().observe(this, Observer {
            if (it)
               showProgressDialog(activity?.supportFragmentManager)
            else
               dismissProgressDialog()
        })

    }

    private fun updateData() {
        editName.afterTextChanged {
            inputName.isErrorEnabled = false
            signupViewModel.setName(it)
        }
        editEmail.afterTextChanged {
            inputEmail.isErrorEnabled = false
            signupViewModel.setEmail(it)
        }
        editMobileNumber.afterTextChanged {
            inputMobileNumber.isErrorEnabled = false
            signupViewModel.setMobile(it)
        }
        editPassword.afterTextChanged {
            inputPassword.isErrorEnabled = false
            signupViewModel.setPassword(it)
        }
        editConfirmPassword.afterTextChanged {
            inputConfirmPassword.isErrorEnabled = false
        }
    }

    private fun setupUI() {
        ViewUtil.animateView(imageLogo,300,500)

        ViewUtil.animateView(inputName,300,500)
        ViewUtil.animateView(inputEmail,300,500)

        ViewUtil.animateView(inputCountryCode,300,500)
        ViewUtil.animateView(inputMobileNumber,300,500)

        ViewUtil.animateView(inputPassword,300,500)
        ViewUtil.animateView(textPasswordHint,300,500)
        ViewUtil.animateView(inputConfirmPassword,300,500)

        ViewUtil.animateView(textTermsCondition,500,500)
        ViewUtil.animateView(buttonSubmit,500,500)

        // set terms and condition and privacy policy
        applySpanTermsConditionPrivacyPolicy(textTermsCondition)

        // set onClick
        headerImageBack.setOnClickListener(this)
        editCountryCode.setOnClickListener(this)
        buttonSubmit.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.headerImageBack -> activity!!.onBackPressed()
            R.id.buttonSubmit ->{
                when{
                    validateData() -> {
                        context?.let { signupViewModel.apiCallSignup(it) }
                    }
                }
            }
        }
    }

    private fun validateData(): Boolean {
        return when {
            TextUtils.isEmpty(editName.text.toString().trim()) -> {
                editName.requestFocus()
                ViewUtil.animateView(inputName, 10, 200)
                inputName.error = getString(R.string.err_please_enter_full_name)
                false
            }
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
            TextUtils.isEmpty(editMobileNumber.text.toString().trim()) -> {
                editMobileNumber.requestFocus()
                ViewUtil.animateView(inputMobileNumber, 10, 200)
                inputMobileNumber.error = getString(R.string.err_please_enter_mobile_number)
                false
            }
            !CommonUtils.isValidPhoneNumber(editMobileNumber.text.toString().trim()) -> {
                editMobileNumber.requestFocus()
                ViewUtil.animateView(inputMobileNumber, 10, 200)
                inputMobileNumber.error = getString(R.string.err_please_enter_valid_mobile_number)
                false
            }
            TextUtils.isEmpty(editPassword.text.toString().trim()) -> {
                editPassword.requestFocus()
                ViewUtil.animateView(inputPassword, 10, 200)
                inputPassword.error = getString(R.string.err_please_enter_password)
                false
            }
            !CommonUtils.isValidPassword(editPassword.text.toString().trim()) -> {
                editPassword.requestFocus()
                ViewUtil.animateView(inputPassword, 10, 200)
                inputPassword.error = getString(R.string.err_please_enter_valid_password)
                false
            }
            TextUtils.isEmpty(editConfirmPassword.text.toString().trim()) -> {
                editConfirmPassword.requestFocus()
                ViewUtil.animateView(inputConfirmPassword, 10, 200)
                inputConfirmPassword.error = getString(R.string.err_please_enter_confirm_password)
                false
            }
            editPassword.text.toString().trim() != editConfirmPassword.text.toString().trim() -> {
                editConfirmPassword.requestFocus()
                ViewUtil.animateView(inputConfirmPassword, 10, 200)
                inputConfirmPassword.error =  getString(R.string.err_password_and_confirm_password_not_match)
                false
            }
            else -> true
        }
    }

    /**
     * Apply specific style to specific text in string
     */
    private fun applySpanTermsConditionPrivacyPolicy(termsCondition: TextView) {
        termsCondition.movementMethod = LinkMovementMethod.getInstance()

        val spannableString = SpannableString(getString(R.string.str_terms_and_condition_privacy_policy))

        val startIndexTC = spannableString.indexOf( getString(R.string.str_terms_and_condition))
        val endIndexTC: Int = startIndexTC + getString(R.string.str_terms_and_condition).length

        val startIndexPP = spannableString.indexOf( getString(R.string.str_privacy_policy))
        val endIndexPP: Int = startIndexPP + getString(R.string.str_privacy_policy).length

        // clickable text
        val clickableSpanTC: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(context,"Terms & Condition Clicked",Toast.LENGTH_SHORT).show()
            }
        }

        spannableString.setSpan(clickableSpanTC, startIndexTC, endIndexTC, 0)

        // clickable text
        val clickableSpanPP: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(context,"Privacy Policy Clicked",Toast.LENGTH_SHORT).show()
            }
        }

        spannableString.setSpan(clickableSpanPP, startIndexPP, endIndexPP, 0)

        spannableString.setSpan(
            activity?.let { ContextCompat.getColor(it, R.color.colorPrimary) }?.let {
                ForegroundColorSpan(it)
            }, startIndexTC, endIndexTC, 0)

        spannableString.setSpan(
            activity?.let { ContextCompat.getColor(it, R.color.colorPrimary) }?.let {
                ForegroundColorSpan(it)
            }, startIndexPP, endIndexPP, 0)

        termsCondition.text = spannableString
    }
}