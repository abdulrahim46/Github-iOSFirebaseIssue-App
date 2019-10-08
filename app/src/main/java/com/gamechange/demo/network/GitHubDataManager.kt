package com.gamechange.demo.network

import com.gamechange.demo.network.models.Issue
import com.gamechange.demo.network.models.IssueComment
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class GitHubDataManager @Inject constructor(private val gitHubService: GitHubService) {

    fun getIssues(): Single<Response<List<Issue>>> {
        return gitHubService.getIssues()
    }

    fun getIssueComments(issueId: Int): Single<Response<List<IssueComment>>> {
        return gitHubService.getIssueComments(issueId)
    }
}
