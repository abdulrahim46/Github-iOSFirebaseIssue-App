@file:Suppress("DEPRECATION")

package com.gamechange.demo.ui.base

import android.app.ProgressDialog
import com.gamechange.demo.helper.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity: DaggerAppCompatActivity() {

    val TAG = this.javaClass.simpleName

    @Inject lateinit var providerFactory: ViewModelProviderFactory

    private var progressDialog: ProgressDialog? = null

    fun showProgressDialog(message: String) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this, ProgressDialog.STYLE_SPINNER)
            progressDialog?.setCancelable(false)
        }
        progressDialog?.setMessage(message)
        progressDialog?.show()
    }

    fun hideProgressDialog() {
        if (progressDialog != null && progressDialog!!.isShowing)
            progressDialog?.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()

        hideProgressDialog()
    }
}
