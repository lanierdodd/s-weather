package com.lanier.weather

import android.app.Application
import android.media.tv.interactive.AppLinkInfo
import com.lanier.weather.logic.network.APIRequester

class BaseApp : Application() {
    companion object {
        const val TOKEN = "2Orir0HGAbcWMRLK"
    }

    override fun onCreate() {
        super.onCreate()
        APIRequester.init()
    }

}