package com.saadahmedsoft.sparkconvo.view.auth.login

import android.os.Bundle
import com.saadahmedsoft.sparkconvo.R
import com.saadahmedsoft.sparkconvo.base.BaseFragment
import com.saadahmedsoft.sparkconvo.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    override val title: String
        get() = ""
    override val isBackButtonVisible: Boolean
        get() = false

    override fun onFragmentCreate(savedInstanceState: Bundle?) {
        //
    }

    override fun observeData() {}
}