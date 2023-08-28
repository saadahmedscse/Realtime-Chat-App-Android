package com.saadahmedsoft.sparkconvo.view.auth.createAccount

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.saadahmedsoft.sparkconvo.R
import com.saadahmedsoft.sparkconvo.base.BaseFragment
import com.saadahmedsoft.sparkconvo.databinding.FragmentCreateAccountDetailsBinding
import com.saadahmedsoft.sparkconvo.helper.delay
import com.saadahmedsoft.sparkconvo.helper.getString
import com.saadahmedsoft.sparkconvo.helper.gone
import com.saadahmedsoft.sparkconvo.helper.onClicked
import com.saadahmedsoft.sparkconvo.helper.setBackground
import com.saadahmedsoft.sparkconvo.helper.setTextColor
import com.saadahmedsoft.sparkconvo.helper.visible
import com.saadahmedsoft.sparkconvo.service.dto.auth.CreateAccountRequest
import java.util.regex.Pattern


class CreateAccountDetailsFragment : BaseFragment<FragmentCreateAccountDetailsBinding>(FragmentCreateAccountDetailsBinding::inflate) {

    override val title: String
        get() = ""
    override val isBackButtonVisible: Boolean
        get() = false

    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>
    private var uri: Uri? = null
    companion object {
        private const val EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    }
    private var btnClickStatus = false
    private var gender = "Male"

    override fun onFragmentCreate(savedInstanceState: Bundle?) {
        binding.layoutChooseImage.onClicked {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                openImagePicker()
            } else {
                if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)
                } else {
                    openImagePicker()
                }
            }
        }

        imagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                uri = result.data?.data
                binding.profilePicture.setImageURI(uri)
            }
        }

        binding.btnMale.onClicked {
            gender = "Male"

            binding.icMale.setBackground(R.drawable.ripple_circle_main)
            binding.tvMale.setTextColor(requireContext(), R.color.static_main)
            binding.icFemale.setBackground(R.drawable.ripple_circle_light_grey)
            binding.tvFemale.setTextColor(requireContext(), R.color.colorLightGrey)
        }

        binding.btnFemale.onClicked {
            gender = "Female"

            binding.icFemale.setBackground(R.drawable.ripple_circle_main)
            binding.tvFemale.setTextColor(requireContext(), R.color.static_main)
            binding.icMale.setBackground(R.drawable.ripple_circle_light_grey)
            binding.tvMale.setTextColor(requireContext(), R.color.colorLightGrey)
        }

        binding.btnNext.onClicked {
            if (!btnClickStatus) {
                binding.tvNext.gone()
                binding.progressBar.visible()
                btnClickStatus = true

                delay(1500) {
                    if (fieldsAreValid()) {
                        val createAccountRequest = CreateAccountRequest(uri!!, binding.etName.getString(), binding.etEmail.getString(), gender)
                        authViewModel.setCreateAccountRequest(createAccountRequest)
                        navigate(R.id.create_account_details_to_create_account_security)
                    }

                    binding.progressBar.gone()
                    binding.tvNext.visible()
                    btnClickStatus = false
                }
            }
        }
    }

    override fun observeData() {}

    private fun fieldsAreValid(): Boolean {
        if (uri == null) {
            "Profile picture is required".shortSnackBar()
            return false
        }
        if (binding.etName.getString().isBlank()) {
            "Name is required".shortSnackBar()
            return false
        }
        if (binding.etEmail.getString().isBlank()) {
            "Email is required".shortSnackBar()
            return false
        }
        if (!Pattern.compile(EMAIL_PATTERN).matcher(binding.etEmail.getString()).matches()) {
            "Invalid email address".shortSnackBar()
            return false
        }

        return true
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        imagePickerLauncher.launch(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openImagePicker()
            } else {
                "You should enable storage permission to choose photos".shortSnackBar()
            }
        }
    }
}