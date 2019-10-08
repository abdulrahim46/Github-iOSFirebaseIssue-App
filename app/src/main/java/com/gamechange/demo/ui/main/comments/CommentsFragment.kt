package com.gamechange.demo.ui.main.comments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gamechange.demo.R
import com.gamechange.demo.helper.TopSpacingItemDecoration
import com.gamechange.demo.ui.base.BaseFragment
import com.gamechange.demo.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_comments.*
import androidx.recyclerview.widget.LinearLayoutManager as LinearLayoutManager1

class CommentsFragment: BaseFragment() {

    private val mainViewModel by lazy { ViewModelProviders.of(activity!!).get(MainViewModel::class.java) }
    private val commentsAdapter by lazy { CommentsAdapter() }
    private var issueNumber: Int = 0

    override fun getLayoutResourceId() = R.layout.fragment_comments

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        issueNumber = arguments!!.getInt("issueNumber")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        attachViewModel()

        baseActivity.showProgressDialog(getString(R.string.fetching_issue_comments))
        mainViewModel.getIssueComments(issueNumber)
    }

    private fun initViews() {
        rvComments.apply {
            layoutManager = LinearLayoutManager1(context)
            val topSpacingDecorator = TopSpacingItemDecoration(10, 5)
            addItemDecoration(topSpacingDecorator)
            adapter = commentsAdapter
        }

        srComments.apply {
            setOnRefreshListener {
                mainViewModel.getIssueComments(issueNumber)
            }
        }
    }

    private fun attachViewModel() {
        mainViewModel.successGetIssueComment.observe(this, Observer { successGetIssueComment ->
            if (successGetIssueComment != null) {
                baseActivity.hideProgressDialog()
                srComments.isRefreshing = false
                if (successGetIssueComment) {
                    publishComments()
                } else {
                    publishComments(true)
                    Toast.makeText(context, getString(R.string.failed_to_fetch_comments), Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun publishComments(failed: Boolean = false) {
        if (mainViewModel.issueComments.isEmpty()) {
            srComments.visibility = View.GONE
            tvNoComments.visibility = View.VISIBLE
            if (failed) {
                tvNoComments.apply {
                    text = getString(R.string.failed_to_get_comments_n_click_to_try_again)
                    setOnClickListener {
                        baseActivity.showProgressDialog(getString(R.string.fetching_issue_comments))
                        mainViewModel.getIssueComments(issueNumber)
                    }
                }
            } else {
                tvNoComments.apply {
                    text = context.getString(R.string.no_comments_found)
                    setOnClickListener {  }
                }
            }
        } else {
            srComments.visibility = View.VISIBLE
            tvNoComments.visibility = View.GONE
            commentsAdapter.publishComments(mainViewModel.issueComments)
        }
    }
}
