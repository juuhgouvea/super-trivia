package com.juuhgouvea.supertrivia

import android.app.Application
import android.content.Context

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        mainContext = this
    }

    companion object {
        var mainContext: Context? = null
        fun getContext(): Context {
            return this.mainContext!!
        }
    }
}