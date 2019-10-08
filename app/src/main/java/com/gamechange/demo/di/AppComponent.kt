package com.gamechange.demo.di

import android.app.Application
import com.gamechange.demo.GameChangeApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ActivitiesBuilderModule::class,
        AppModule::class,
        ViewModelFactoryModule::class]
)
interface AppComponent: AndroidInjector<GameChangeApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
