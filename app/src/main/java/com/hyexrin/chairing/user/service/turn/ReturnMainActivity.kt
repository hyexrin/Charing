package com.hyexrin.chairing.user.service.turn

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyexrin.chairing.R
import com.hyexrin.chairing.user.HomeFragment
import kotlinx.android.synthetic.main.activity_return_main.*

class ReturnMainActivity : AppCompatActivity() {

//    var RentList = arrayListOf<RentInfo>(
//            RentInfo(1,"211012","211013", 1),
//            RentInfo(2,"211012","211013", 2),
//            RentInfo(3,"211012","211013", 3),
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_return_main)

        //         user 값 받아오기
        val iintent = intent
        val userID = iintent.getStringExtra("UserID")
        val userName = iintent.getStringExtra("UserName")
        val userPass = iintent.getStringExtra("UserPass")

        Log.d("ReturnMain - 진입", "성공")
        val intent = intent
        val RentList = intent.getSerializableExtra("RentList")
        Log.d("ReturnMain - intent 성공", RentList.toString())
//
//
        rvRent.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvRent.setHasFixedSize(true)
//
        rvRent.adapter = ReturnAdapter(RentList as ArrayList<RentInfo>)
        Log.d("ReturnMain - adapter 연결", "성공")
    }
//    211019 주석
    override fun onBackPressed() {
        super.onBackPressed()

//         user 값 받아오기
        val intent = intent
        val userID = intent.getStringExtra("UserID")
        val userName = intent.getStringExtra("UserName")
        val userPass = intent.getStringExtra("UserPass")

        val subIntent = Intent(this@ReturnMainActivity, HomeFragment::class.java) //지금 액티비티에서 다른 액티비티로 이동하는 인텐트 설정
        subIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP //인텐트 플래그 설정

        subIntent.putExtra("UserID", userID)
        subIntent.putExtra("UserPass", userPass)
        subIntent.putExtra("UserName", userName)
        startActivity(subIntent)
        finish()

    }
}
