package com.hyexrin.chairing.user.service.rent

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.hyexrin.chairing.R
import kotlinx.android.synthetic.main.activity_call.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.imgMain
import kotlinx.android.synthetic.main.activity_login.imgPre
import kotlinx.android.synthetic.main.activity_mem_search.*
import kotlinx.android.synthetic.main.activity_now_rent_sub.*
import org.json.JSONException
import org.json.JSONObject

class CallActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)

        // user 값 받아오기
        val intent = intent
        val userID = intent.getStringExtra("UserID")
        val userName = intent.getStringExtra("UserName")
        val userPass = intent.getStringExtra("UserPass")
        Log.d("CallRent - get","${userID} 값 받아오기 성공")

        // 상단 이미지 버튼 클릭 시 수행
        imgMain.setOnClickListener {
            val intent = Intent(this@CallActivity, RentMainActivity::class.java)
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

        var place = resources.getStringArray(R.array.facility)
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, place)
        var selectPlace = "null"
        spinner2.adapter = adapter

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> {
                        selectPlace = "대여소를 선택해주세요."
                    }
                    1 -> {
                        selectPlace = "1"
                    }
                    2 -> {
                        selectPlace = "2"
                    }
                    3 -> {
                        selectPlace = "3"
                    }
                    4 -> {
                        selectPlace = "4"
                    }
                    5 -> {
                        selectPlace = "5"
                    }
                    6 -> {
                        selectPlace = "6"
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        // 호출하기 버튼 클릭 시 수행
        btnCall.setOnClickListener(View.OnClickListener { // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.

            val intent = intent
            val userID = intent.getStringExtra("UserID")
            val userName = intent.getStringExtra("UserName")
            val userPass = intent.getStringExtra("UserPass")

//            val number = 2
//            val rentTime = "NOW()"
//            val returntime = "0000-00-00 00:00:00"
            val user = userID
            val wheelcode = 1
            val rentplace = selectPlace.toInt()
            val returnplace = 1
            val time = "2h"
            val how = 0 // 부르기
            val responseListener: Response.Listener<String?> = Response.Listener { response ->
                try {
                    Log.d("CallActivity", "- try")
                    val jsonObject = JSONObject(response)
                    val success = jsonObject.getBoolean("success")
                    if (success) { // 부르기에 성공한 경우
                        Toast.makeText(applicationContext, "10${selectPlace}로 휠체어를 호출하였습니다.", Toast.LENGTH_SHORT)
                                .show()
                        val intent = Intent(this@CallActivity, RentMainActivity::class.java)
                        intent.putExtra("UserID", userID)
                        intent.putExtra("UserPass", userPass)
                        intent.putExtra("UserName", userName)
                        startActivity(intent)
                    } else { // 부르기에 실패한 경우
                        Log.d("CallActivity", "- else")
                        Toast.makeText(applicationContext, "휠체어 호출에 실패하였습니다.", Toast.LENGTH_SHORT)
                                .show()
                        return@Listener
                    }
                } catch (e: JSONException) {
                    Log.d("CallActivity", "- catch")
                    e.printStackTrace()
                }
            }
            // 서버로 Volley를 이용해서 요청을 함.
            val rentRequest =
                    RentRequest(user, wheelcode, rentplace, returnplace, time, how, responseListener)
            val queue = Volley.newRequestQueue(this@CallActivity)
            queue.add(rentRequest)
        })

        if (Build.VERSION.SDK_INT < 16) {
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }


    }
}