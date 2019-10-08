package com.gamechange.demo.network

import com.gamechange.demo.network.models.Issue
import com.gamechange.demo.network.models.IssueComment
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("issues")
    fun getIssues(): Single<Response<List<Issue>>>

    @GET("issues/{issueId}/comments")
    fun getIssueComments(@Path("issueId") issueId: Int): Single<Response<List<IssueComment>>>
}
