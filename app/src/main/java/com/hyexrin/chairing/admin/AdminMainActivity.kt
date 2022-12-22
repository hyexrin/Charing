package com.hyexrin.chairing.admin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hyexrin.chairing.MainActivity
import com.hyexrin.chairing.R
import com.hyexrin.chairing.admin.Wheel.WheelMainActivity
import kotlinx.android.synthetic.main.activity_admin_main.*

class AdminMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_main)

//        btnMemManage.setOnClickListener{
//            val intent = Intent(this, MemMainActivity::class.java)
//            startActivity(intent)
//        }
        btnWheelManager.setOnClickListener{
            val intent = Intent(this, WheelMainActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@AdminMainActivity, MainActivity::class.java) //지금 액티비티에서 다른 액티비티로 이동하는 인텐트 설정
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP //인텐트 플래그 설정
        startActivity(intent) //인텐트 이동
        finish() //현재 액티비티 종료
    }

}