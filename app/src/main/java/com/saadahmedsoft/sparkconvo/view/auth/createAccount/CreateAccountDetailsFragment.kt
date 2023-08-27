package com.saadahmedsoft.sparkconvo.view.auth.createAccount

import android.os.Bundle
import com.saadahmedsoft.sparkconvo.base.BaseFragment
import com.saadahmedsoft.sparkconvo.databinding.FragmentCreateAccountDetailsBinding

class CreateAccountDetailsFragment : BaseFragment<FragmentCreateAccountDetailsBinding>(FragmentCreateAccountDetailsBinding::inflate) {

    override val title: String
        get() = ""
    override val isBackButtonVisible: Boolean
        get() = false

    override fun onFragmentCreate(savedInstanceState: Bundle?) {
        //
    }

    override fun observeData() {}
}