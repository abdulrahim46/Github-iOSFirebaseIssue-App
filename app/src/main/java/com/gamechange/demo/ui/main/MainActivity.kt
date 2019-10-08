package com.gamechange.demo.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.gamechange.demo.R
import com.gamechange.demo.network.GitHubDataManager
import com.gamechange.demo.ui.base.BaseActivity
import javax.inject.Inject

class MainActivity: BaseActivity() {

    private val mainViewModel by lazy { ViewModelProviders.of(this, providerFactory).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.getIssues()
    }
}
