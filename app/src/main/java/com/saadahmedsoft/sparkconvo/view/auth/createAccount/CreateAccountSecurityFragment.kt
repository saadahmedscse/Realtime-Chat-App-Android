package com.saadahmedsoft.sparkconvo.view.auth.createAccount

import android.content.Intent
import android.os.Bundle
import com.google.gson.JsonObject
import com.saadahmedsoft.sparkconvo.base.BaseFragment
import com.saadahmedsoft.sparkconvo.databinding.FragmentCreateAccountSecurityBinding
import com.saadahmedsoft.sparkconvo.helper.createBody
import com.saadahmedsoft.sparkconvo.helper.delay
import com.saadahmedsoft.sparkconvo.helper.getString
import com.saadahmedsoft.sparkconvo.helper.gone
import com.saadahmedsoft.sparkconvo.helper.observe
import com.saadahmedsoft.sparkconvo.helper.onClicked
import com.saadahmedsoft.sparkconvo.helper.visible
import com.saadahmedsoft.sparkconvo.service.dto.auth.CreateAccountRequest
import com.saadahmedsoft.sparkconvo.util.MultiPartUtil
import com.saadahmedsoft.sparkconvo.view.dashboard.DashboardActivity
import okhttp3.MultipartBody
import okhttp3.RequestBody

class CreateAccountSecurityFragment : BaseFragment<FragmentCreateAccountSecurityBinding>(FragmentCreateAccountSecurityBinding::inflate) {

    private lateinit var createAccountRequest: CreateAccountRequest
    private var photoPart: MultipartBody.Part? = null
    private lateinit var map: MutableMap<String, RequestBody>
    private var btnClickStatus = false

    override fun onFragmentCreate(savedInstanceState: Bundle?) {
        map = mutableMapOf()

        binding.btnBack.onClicked {
            onBackPressed()
        }

        binding.btnCreateAccount.onClicked {
            if (!btnClickStatus) {
                binding.tvNext.gone()
                binding.progressBar.visible()
                btnClickStatus = true

                delay(1500) {
                    if (fieldsAreValid()) {
                        photoPart = MultiPartUtil.getInstance(requireContext()).getPartFromUri(createAccountRequest.photo, "photo")
                        map["name"] = createAccountRequest.name.createBody()
                        map["email"] = createAccountRequest.email.createBody()
                        map["gender"] = createAccountRequest.gender.createBody()
                        map["password"] = binding.etPassword.createBody()
                        map["confirmPassword"] = binding.etConfirmPassword.createBody()

                        apiService.createAccount(map, photoPart).getResponse("Creating your account, please wait.") {
                            it.status?.let { createAccountStatus ->
                                if (createAccountStatus) {
                                    val body = JsonObject()
                                    body.addProperty("email", createAccountRequest.email)
                                    body.addProperty("password", binding.etPassword.getString())

                                    apiService.login(body).getResponse("Trying to login, please wait.") { loginResponse ->
                                        loginResponse.status?.let { loginStatus ->
                                            if (loginStatus) {
                                                session.setBearerToken(loginResponse.token!!)

                                                val intent = Intent(requireContext(), DashboardActivity::class.java)
                                                requireActivity().startActivity(intent)
                                                requireActivity().finish()
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    binding.progressBar.gone()
                    binding.tvNext.visible()
                    btnClickStatus = false
                }
            }
        }
    }

    override fun observeData() {
        observe(authViewModel.createAccountRequest) {
            createAccountRequest = it
        }
    }

    private fun fieldsAreValid(): Boolean {
        if (binding.etPassword.getString().isBlank()) {
            "Password is required".shortSnackBar()
            return false
        }
        if (binding.etConfirmPassword.getString().isBlank()) {
            "Confirm password is required".shortSnackBar()
            return false
        }
        if (binding.etPassword.getString() != binding.etConfirmPassword.getString()) {
            "Password didn't match".shortSnackBar()
            return false
        }

        return true
    }
}