package com.saadahmedsoft.sparkconvo.view.auth.login

import android.content.Intent
import android.os.Bundle
import com.google.gson.JsonObject
import com.saadahmedsoft.sparkconvo.R
import com.saadahmedsoft.sparkconvo.base.BaseFragment
import com.saadahmedsoft.sparkconvo.databinding.FragmentLoginBinding
import com.saadahmedsoft.sparkconvo.helper.delay
import com.saadahmedsoft.sparkconvo.helper.getString
import com.saadahmedsoft.sparkconvo.helper.gone
import com.saadahmedsoft.sparkconvo.helper.onClicked
import com.saadahmedsoft.sparkconvo.helper.visible
import com.saadahmedsoft.sparkconvo.view.dashboard.DashboardActivity
import java.util.regex.Pattern

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    companion object {
        private const val EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    }

    private var btnClickStatus = false

    override fun onFragmentCreate(savedInstanceState: Bundle?) {
        binding.btnGetStarted.onClicked {
            navigate(R.id.login_to_create_account)
        }

        binding.btnLogin.onClicked {
            if (!btnClickStatus) {
                binding.tvLogin.gone()
                binding.progressBar.visible()
                btnClickStatus = true

                delay(1500) {
                    if (fieldsAreValid()) {
                        val body = JsonObject()
                        body.addProperty("email", binding.etEmail.getString())
                        body.addProperty("password", binding.etPassword.getString())

                        apiService.login(body).getResponse("Logging you in, please wait.") {
                            it.status?.let { status ->
                                if (status) {
                                    session.setBearerToken(it.token!!)
                                    val intent = Intent(requireContext(), DashboardActivity::class.java)
                                    requireActivity().startActivity(intent)
                                    requireActivity().finish()
                                }
                            }
                        }
                    }

                    binding.progressBar.gone()
                    binding.tvLogin.visible()
                    btnClickStatus = false
                }
            }
        }
    }

    override fun observeData() {}

    private fun fieldsAreValid(): Boolean {
        if (binding.etEmail.getString().isBlank()) {
            "Email is required".shortSnackBar()
            return false
        }
        if (!Pattern.compile(EMAIL_PATTERN).matcher(binding.etEmail.getString()).matches()) {
            "Invalid email address".shortSnackBar()
            return false
        }
        if (binding.etPassword.getString().isBlank()) {
            "Password is required".shortSnackBar()
            return false
        }

        return true
    }
}