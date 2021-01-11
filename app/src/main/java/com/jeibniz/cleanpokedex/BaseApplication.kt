package com.jeibniz.cleanpokedex

import android.app.Application
import com.jeibniz.cleanpokedex.di.AppComponent
import com.jeibniz.cleanpokedex.di.DaggerAppComponent

class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    // Visible so that we can override this for tests.
    fun initAppComponent() {
        appComponent = DaggerAppComponent.factory().create(this)
    }
}