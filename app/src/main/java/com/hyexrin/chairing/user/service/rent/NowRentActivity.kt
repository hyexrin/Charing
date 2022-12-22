package com.hyexrin.chairing.user.service.rent

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hyexrin.chairing.R
import com.journeyapps.barcodescanner.CaptureManager
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.imgMain
import kotlinx.android.synthetic.main.activity_login.imgPre
import kotlinx.android.synthetic.main.activity_now_rent.*

class NowRentActivity : AppCompatActivity() {
    private lateinit var capture: CaptureManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_now_rent)

        // user 값 받아오기
        val intent = intent
        val userID = intent.getStringExtra("UserID")
        val userName = intent.getStringExtra("UserName")
        val userPass = intent.getStringExtra("UserPass")

        // 상단 이미지 버튼 클릭 시 수행
        imgMain.setOnClickListener {
            val intent = Intent(this@NowRentActivity, RentMainActivity::class.java)
            intent.putExtra("UserID", userID)
            intent.putExtra("UserPass", userPass)
            intent.putExtra("UserName", userName)
            startActivity(intent)
            finish()
        }
        imgPre.setOnClickListener {
//            val intent = Intent(this@RentMainActivity, UserMainActivity::class.java)
//            startActivity(intent)
            finish()
        }


        val barcodeScanner = findViewById<com.journeyapps.barcodescanner.DecoratedBarcodeView>(R.id.barcodeScanner)
        capture = CaptureManager(this@NowRentActivity, barcodeScanner)
        capture.initializeFromIntent(intent, savedInstanceState)
        capture.decode()
    }
    override fun onResume() {
        super.onResume()
        capture.onResume()
    }
    override fun onPause() {
        super.onPause()
        capture.onPause()
    }
    override fun onDestroy() {
        super.onDestroy()
        capture.onDestroy()
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        capture.onSaveInstanceState(outState)
    }
    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        capture.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}