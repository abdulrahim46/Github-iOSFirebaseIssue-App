package com.gamechange.demo.di

import android.app.Application
import android.content.Context
import com.gamechange.demo.network.ApiManagers
import com.gamechange.demo.network.GitHubService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    internal fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    internal fun provideGitHubService(): GitHubService {
        return ApiManagers.instance.create(GitHubService::class.java)
    }
}
