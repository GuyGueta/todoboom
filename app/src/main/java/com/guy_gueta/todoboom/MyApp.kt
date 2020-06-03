package com.guy_gueta.todoboom

import android.app.Application

class MyApp : Application()
{

    lateinit var manager: TodoAppDatabase

    override fun onCreate() {
        super.onCreate()
        manager = TodoAppDatabase(this)
    }
}