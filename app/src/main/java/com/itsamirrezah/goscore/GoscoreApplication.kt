package com.itsamirrezah.goscore

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.itsamirrezah.goscore.util.SharedPreferencesUtil

class GoscoreApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupAppTheme()
    }

    private fun setupAppTheme() {
        if (SharedPreferencesUtil.getInstance(this).isNightMode)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}