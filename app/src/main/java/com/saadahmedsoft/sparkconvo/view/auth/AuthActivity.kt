package com.saadahmedsoft.sparkconvo.view.auth

import android.os.Bundle
import com.saadahmedsoft.sparkconvo.base.BaseActivity
import com.saadahmedsoft.sparkconvo.databinding.ActivityAuthBinding
import com.saadahmedsoft.sparkconvo.databinding.AppToolbarBinding

class AuthActivity : BaseActivity<ActivityAuthBinding>(ActivityAuthBinding::inflate) {

    override val toolbarBinding: AppToolbarBinding?
        get() = null

    override fun onActivityCreate(savedInstanceState: Bundle?) {
        //
    }

    override fun observeData() {}
}