package com.saadahmedsoft.sparkconvo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.saadahmedsoft.sparkconvo.helper.snackBar
import com.saadahmedsoft.sparkconvo.helper.toast
import com.saadahmedsoft.sparkconvo.util.ApiCall
import com.saadahmedsoft.sparkconvo.util.SessionManager
import com.saadahmedsoft.tinydb.TinyDB
import retrofit2.Call

abstract class BaseActivity<BINDING: ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> BINDING
) : AppCompatActivity() {

    private lateinit var _binding: BINDING
    private lateinit var _session: SessionManager
    private lateinit var _tinyDb: TinyDB

    val binding: BINDING
        get() = _binding

    val session: SessionManager
        get() = _session

    val tinyDb: TinyDB
        get() = _tinyDb

    private var pageTitle = ""

    abstract fun onActivityCreate(savedInstanceState: Bundle?)

    abstract fun observeData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        _session = SessionManager.getInstance(this)
        _tinyDb = TinyDB.getInstance(this)
        setContentView(_binding.root)
        observeData()
        onActivityCreate(savedInstanceState)
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
        snackBar(this, _binding.root, message, duration)
    }

    private fun showToast(message: String, duration: Int) {
        toast(this, message, duration)
    }

    fun <T> Call<T>.getResponse(progressMessage: String, listener: ApiCall.OnResponseGet<T>) {
        ApiCall.enqueue(this@BaseActivity, this, progressMessage) {
            listener.onSuccessful(it)
        }
    }
}