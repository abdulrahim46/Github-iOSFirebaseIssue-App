package com.gamechange.demo.ui.main

import android.os.Bundle
import androidx.navigation.Navigation
import com.gamechange.demo.R
import com.gamechange.demo.ui.base.BaseActivity

class MainActivity: BaseActivity() {

    val navController by lazy { Navigation.findNavController(this, R.id.main_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
