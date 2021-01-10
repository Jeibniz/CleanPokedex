package com.jeibniz.cleanpokedex

import android.app.Application
import com.jeibniz.cleanpokedex.di.DaggerAppComponent

class BaseApplication : Application() {
    val appComponent = DaggerAppComponent.create()
}