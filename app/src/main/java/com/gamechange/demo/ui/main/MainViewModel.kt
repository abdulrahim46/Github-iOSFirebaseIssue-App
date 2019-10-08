package com.gamechange.demo.ui.main

import androidx.lifecycle.MutableLiveData
import com.gamechange.demo.network.GitHubDataManager
import com.gamechange.demo.network.models.Issue
import com.gamechange.demo.network.models.IssueComment
import com.gamechange.demo.ui.base.BaseViewModel
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class MainViewModel@Inject internal constructor(
    private val gitHubDataManager: GitHubDataManager
): BaseViewModel() {

    val successGetIssues: MutableLiveData<Boolean> = MutableLiveData()
    val successGetIssueComment: MutableLiveData<Boolean> = MutableLiveData()

    val issues = mutableListOf<Issue>()
    val issueComments = mutableListOf<IssueComment>()

    fun getIssues() {
        gitHubDataManager.getIssues().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Response<List<Issue>>> {
                override fun onSubscribe(d: Disposable) {
                    addDisposable(d)
                }
                override fun onSuccess(listResponse: Response<List<Issue>>) {
                    issues.clear()
                    issues.addAll(listResponse.body()!!)
                    successGetIssues.value = true
                }
                override fun onError(e: Throwable) {
                    successGetIssues.value = false
                }
            })
    }

    fun getIssueComments(issueId: Int) {
        gitHubDataManager.getIssueComments(issueId).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Response<List<IssueComment>>> {
                override fun onSubscribe(d: Disposable) {
                    addDisposable(d)
                }
                override fun onSuccess(listResponse: Response<List<IssueComment>>) {
                    issueComments.clear()
                    issueComments.addAll(listResponse.body()!!)
                    successGetIssueComment.value = true
                }
                override fun onError(e: Throwable) {
                    successGetIssueComment.value = false
                }
            })
    }
}
