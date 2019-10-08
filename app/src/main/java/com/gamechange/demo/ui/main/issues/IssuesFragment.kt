package com.gamechange.demo.ui.main.issues

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.gamechange.demo.R
import com.gamechange.demo.helper.TopSpacingItemDecoration
import com.gamechange.demo.ui.base.BaseFragment
import com.gamechange.demo.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_issues.*

class IssuesFragment: BaseFragment() {

    private val mainViewModel by lazy { ViewModelProviders.of(activity!!, providerFactory).get(MainViewModel::class.java) }
    private val issuesAdapter by lazy { IssuesAdapter() }

    override fun getLayoutResourceId() = R.layout.fragment_issues

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        attachViewModel()

        if (mainViewModel.issues.isEmpty()) {
            baseActivity.showProgressDialog(getString(R.string.feyching_github_issues))
            mainViewModel.getIssues()
        } else {
            publishIssues()
        }

        mainViewModel.getIssues()
    }

    private fun initViews() {
        rvIssues.apply {
            layoutManager = LinearLayoutManager(context)
            val topSpacingDecorator = TopSpacingItemDecoration(10, 5)
            addItemDecoration(topSpacingDecorator)
            adapter = issuesAdapter
        }

        srIssues.apply {
            setOnRefreshListener {
                mainViewModel.getIssues()
            }
        }
    }

    private fun attachViewModel() {
        mainViewModel.successGetIssues.observe(this, Observer { successPastCases ->
            if (successPastCases != null) {
                baseActivity.hideProgressDialog()
                srIssues.isRefreshing = false
                if (successPastCases) {
                    publishIssues()
                } else {
                    publishIssues(true)
                    Toast.makeText(context, getString(R.string.failed_to_fetch_issue_list), Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun publishIssues(failed: Boolean = false) {
        if (mainViewModel.issues.isEmpty()) {
            srIssues.visibility = View.GONE
            tvNoIssues.visibility = View.VISIBLE
            if (failed) {
                tvNoIssues.apply {
                    text = getString(R.string.failed_to_get_issues_n_click_to_try_again)
                    setOnClickListener {
                        baseActivity.showProgressDialog(getString(R.string.feyching_github_issues))
                        mainViewModel.getIssues()
                    }
                }
            } else {
                tvNoIssues.apply {
                    text = context.getString(R.string.no_issues_found)
                    setOnClickListener {  }
                }
            }
        } else {
            srIssues.visibility = View.VISIBLE
            tvNoIssues.visibility = View.GONE
            issuesAdapter.publishIssues(mainViewModel.issues)
        }
    }
}
