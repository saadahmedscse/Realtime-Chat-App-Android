package com.saadahmedsoft.sparkconvo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.saadahmedsoft.sparkconvo.api.RetroInstance
import com.saadahmedsoft.sparkconvo.helper.navigate
import com.saadahmedsoft.sparkconvo.helper.snackBar
import com.saadahmedsoft.sparkconvo.helper.toast
import com.saadahmedsoft.sparkconvo.util.ApiCall
import com.saadahmedsoft.sparkconvo.util.SessionManager
import com.saadahmedsoft.sparkconvo.viewmodel.AuthViewModel
import com.saadahmedsoft.sparkconvo.viewmodel.ToolbarViewModel
import com.saadahmedsoft.tinydb.TinyDB
import retrofit2.Call

abstract class BaseFragment<BINDING: ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> BINDING
) : Fragment() {

    private lateinit var _binding: BINDING
    private lateinit var _session: SessionManager
    private lateinit var _tinyDb: TinyDB
    private val toolbarViewModel by activityViewModels<ToolbarViewModel>()
    val authViewModel by activityViewModels<AuthViewModel>()
    val apiService = RetroInstance.retrofitInstance

     val binding: BINDING
        get() = _binding

    val session: SessionManager
        get() = _session

    val tinyDb: TinyDB
        get() = _tinyDb

    abstract val title: String
    abstract val isBackButtonVisible: Boolean

    abstract fun onFragmentCreate(savedInstanceState: Bundle?)
    abstract fun observeData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        _session = SessionManager.getInstance(requireContext())
        _tinyDb = TinyDB.getInstance(requireContext())
        observeData()
        onFragmentCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbarViewModel.setTitle(title)
        toolbarViewModel.setBackButtonState(isBackButtonVisible)
        return _binding.root
    }

    fun String?.shortSnackBar() {
        this?.let { showSnackBar(it, Snackbar.LENGTH_SHORT) }
    }

    fun String?.longSnackBar() {
        this?.let { showSnackBar(it, Snackbar.LENGTH_LONG) }
    }

    fun String?.shortToast() {
        this?.let { showToast(it, Toast.LENGTH_SHORT) }
    }

    fun String?.longToast() {
        this?.let { showToast(it, Toast.LENGTH_LONG) }
    }

    private fun showSnackBar(message: String, duration: Int) {
        snackBar(requireContext(), _binding.root, message, duration)
    }

    private fun showToast(message: String, duration: Int) {
        toast(requireContext(), message, duration)
    }

    fun navigate(@IdRes id: Int) {
        binding.root.navigate(id)
    }

    fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    fun <T> Call<T>.getResponse(listener: ApiCall.OnResponseGet<T>) {
        ApiCall.enqueue(requireContext(), this) {
            listener.onSuccessful(it)
        }
    }

    fun <T> Call<T>.getNoProgressResponse(listener: ApiCall.OnResponseGet<T>) {
        ApiCall.enqueueNoProgress(requireContext(), this) {
            listener.onSuccessful(it)
        }
    }
}