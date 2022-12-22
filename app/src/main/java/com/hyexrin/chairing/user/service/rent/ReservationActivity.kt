package com.hyexrin.chairing.user.service.rent

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hyexrin.chairing.R
import kotlinx.android.synthetic.main.activity_reservation.*

class ReservationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        // user 값 받아오기
        val intent = intent
        val userID = intent.getStringExtra("UserID")
        val userName = intent.getStringExtra("UserName")
        val userPass = intent.getStringExtra("UserPass")

        // 상단 이미지 버튼 클릭 시 수행
        imgMain.setOnClickListener {
            val intent = Intent(this@ReservationActivity, RentMainActivity::class.java)
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
    }
}