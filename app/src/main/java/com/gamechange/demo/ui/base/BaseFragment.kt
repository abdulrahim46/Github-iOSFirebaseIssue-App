package com.gamechange.demo.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.gamechange.demo.helper.ViewModelProviderFactory
import dagger.android.support.DaggerFragment

abstract class BaseFragment: DaggerFragment() {

    val TAG = this.javaClass.simpleName

    val providerFactory by lazy { (activity as BaseActivity).providerFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutResourceId(), container, false)
    }

    @LayoutRes
    protected abstract fun getLayoutResourceId(): Int

    val baseActivity: BaseActivity
        get() = activity as BaseActivity
}
