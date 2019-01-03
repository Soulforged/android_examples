package io.personal.dicardo.myinstagram

import android.app.Application
import com.parse.*


class StarterApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Parse.enableLocalDatastore(this);

        Parse.initialize(Parse.Configuration.Builder(applicationContext)
            .applicationId("1d47a8bf93be6105b826e6f20131f0e140367da2")
            .clientKey("e89c423f92b90ae52109c0e1e7fc441724d7fd3b")
            .server("http://ec2-18-188-200-106.us-east-2.compute.amazonaws.com/parse/")
            .build())

        val defaultACL = ParseACL()
        defaultACL.publicReadAccess = true
        defaultACL.publicWriteAccess = true
        ParseACL.setDefaultACL(defaultACL, true)
    }
}