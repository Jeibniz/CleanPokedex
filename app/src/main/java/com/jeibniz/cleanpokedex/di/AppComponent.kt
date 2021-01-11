package com.jeibniz.cleanpokedex.di

import com.jeibniz.cleanpokedex.BaseApplication
import com.jeibniz.cleanpokedex.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        PokedexFragmentFactoryModule::class,
        PokedexViewModelFactoryModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: BaseApplication): AppComponent
    }

    fun inject(activity: MainActivity)
}