package com.aisier.ui

import android.os.Bundle
import android.view.View
import com.aisier.R
import com.aisier.architecture.base.BaseActivity
import com.aisier.databinding.ActivitySecondBinding
import com.ldlywt.colorful.ColorTheme
import com.ldlywt.colorful.ThemeStyle

class SecondActivity : BaseActivity<ActivitySecondBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<View>(R.id.bt_change_theme).setOnClickListener {
            ColorTheme().edit()
                .setDarkTheme(false)
                .setCustomThemeOverride(ThemeStyle.values().random().res)
                .applyAndRestart(this)
        }
    }
}