package com.gamechange.demo.di

import com.gamechange.demo.ui.main.comments.CommentsFragment
import com.gamechange.demo.ui.main.issues.IssuesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun constributeIssuesFragment(): IssuesFragment

    @ContributesAndroidInjector
    internal abstract fun constributeCommentsFragment(): CommentsFragment
}
