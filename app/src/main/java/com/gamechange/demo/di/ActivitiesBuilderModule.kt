package com.gamechange.demo.di

import com.gamechange.demo.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBuilderModule {

    @ContributesAndroidInjector(modules = [FragmentsBuilderModule::class])
    internal abstract fun contributeMainActivity(): MainActivity
}
