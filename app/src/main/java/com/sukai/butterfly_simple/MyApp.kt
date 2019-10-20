package com.sukai.butterfly_simple

import android.app.Application
import com.sukai.butterfly_simple.runtime.ActivityBuilder

/**
 * Created by sukaidev on 2019/10/03.
 */
class MyApp :Application(){

    override fun onCreate() {
        super.onCreate()
        ActivityBuilder.INSTANCE.init(this)
    }
}