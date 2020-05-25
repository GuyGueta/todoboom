package com.guy_gueta.todoboom

import android.app.Application

class MyApp : Application()
{
    lateinit var appManger : TodoAppManger

    override fun onCreate() {
        super.onCreate()
        appManger = TodoAppManger(this)
        appManger.getData()
    }
}