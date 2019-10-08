package com.gamechange.demo.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.gamechange.demo.R
import com.gamechange.demo.ui.base.BaseFragment

class CommentsFragment: BaseFragment() {

    private val mainViewModel by lazy { ViewModelProviders.of(activity!!).get(MainViewModel::class.java) }

    override fun getLayoutResourceId() = R.layout.fragment_comments

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}
