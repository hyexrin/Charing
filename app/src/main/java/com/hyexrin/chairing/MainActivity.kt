package com.hyexrin.chairing

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (Build.VERSION.SDK_INT < 16) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        actionBar?.hide()


        val InformIntent = Intent(this, InformActivity::class.java) // 인텐트를 생성
        btnGoInfo.setOnClickListener {
            startActivity(InformIntent)
        }

        val LoginIntent = Intent(this, LoginActivity::class.java) // 인텐트를 생성
        btnGoLog.setOnClickListener {
            startActivity(LoginIntent)
        }

    }
}