package com.hyexrin.chairing.user.service.rent

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.hyexrin.chairing.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.imgMain
import kotlinx.android.synthetic.main.activity_login.imgPre
import kotlinx.android.synthetic.main.activity_now_rent_sub.*
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONException
import org.json.JSONObject

class NowRentSubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_now_rent_sub)

        // user 값 받아오기
        val intent = intent
        val userID = intent.getStringExtra("UserID")
        val userName = intent.getStringExtra("UserName")
        val userPass = intent.getStringExtra("UserPass")
        val code = intent.getStringExtra("code")

        Log.d("NowSub - get", "${userID} 받아오기 성공")

        rentCode.setText(code)
        // 상단 이미지 버튼 클릭 시 수행
        imgMain.setOnClickListener {
            val intent = Intent(this@NowRentSubActivity, RentMainActivity::class.java)
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

        // 지금 사용하기 버튼 클릭 시 수행
        btnNow.setOnClickListener(View.OnClickListener { // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
            Log.d("NowSub - btn", "btn Click")
//            val number = 5
//            val rentTime = "NOW()"
//            val returntime = "0000-00-00 00:00:00"
            val user = userID
            val wheelcode = 1
            val rentplace = 0
            val returnplace = 0
            val time = "2h"
            val how = 1 // QR

            Log.d("NowSub - test", "${wheelcode}, ${user}")

            val responseListener: Response.Listener<String?> = Response.Listener { response ->
                try {
                    Log.d("NowSub - try", "try 성공")
                    val jsonObject = JSONObject(response)
                    val success = jsonObject.getBoolean("success")
                    if (success) { // 대여에 성공한 경우
                        Log.d("NowSub - if", "if 성공")
                        Toast.makeText(applicationContext, "휠체어 대여에 성공하였습니다.", Toast.LENGTH_SHORT)
                                .show()
                        val intent = Intent(this@NowRentSubActivity, RentMainActivity::class.java)
                        startActivity(intent)
                    } else { // 대여에 실패한 경우
                        Log.d("NowSub - if", "if 실패")
                        Toast.makeText(applicationContext, "휠체어 대여에 실패하였습니다.", Toast.LENGTH_SHORT)
                                .show()
                        return@Listener
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            // 서버로 Volley를 이용해서 요청을 함.
            val rentRequest =
                    RentRequest(user, wheelcode, rentplace, returnplace, time, how, responseListener)
            val queue = Volley.newRequestQueue(this@NowRentSubActivity)
            queue.add(rentRequest)
        })

        if (Build.VERSION.SDK_INT < 16) {
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

    }
}