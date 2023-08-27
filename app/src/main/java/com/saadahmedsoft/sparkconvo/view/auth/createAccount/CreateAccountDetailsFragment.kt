package com.saadahmedsoft.sparkconvo.view.auth.createAccount

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.saadahmedsoft.sparkconvo.base.BaseFragment
import com.saadahmedsoft.sparkconvo.databinding.FragmentCreateAccountDetailsBinding
import com.saadahmedsoft.sparkconvo.helper.onClicked


class CreateAccountDetailsFragment : BaseFragment<FragmentCreateAccountDetailsBinding>(FragmentCreateAccountDetailsBinding::inflate) {

    override val title: String
        get() = ""
    override val isBackButtonVisible: Boolean
        get() = false

    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>

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
                val uri = result.data?.data
                binding.profilePicture.setImageURI(uri)
            }
        }
    }

    override fun observeData() {}

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