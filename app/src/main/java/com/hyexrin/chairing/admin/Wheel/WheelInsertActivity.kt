package com.hyexrin.chairing.admin.Wheel

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.hyexrin.chairing.R
import com.hyexrin.chairing.admin.Wheel.WheelRequest.WheelInsertRequest
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_wheel_insert.*
import org.json.JSONException
import org.json.JSONObject

class WheelInsertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wheel_insert)

        btnInsertWheel.setOnClickListener(View.OnClickListener {  // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.

//            val wheelCode = etWheelCode.getText().toString().toInt()
            val wheelName = etWheelName.getText().toString()
            val wheelDate = etWheelDate.getText().toString()
            val wheelRent = etWheelRent.getText().toString()
            val wheelBreak = etWheelBreak.getText().toString()

            val responseListener: Response.Listener<String?> = Response.Listener { response ->
                try {
                    val jsonObject = JSONObject(response)
                    val success = jsonObject.getBoolean("success")
                    if (success) { // 휠체어등록에 성공한 경우
                        Toast.makeText(applicationContext, wheelName + " - 휠체어 등록에 성공하였습니다.", Toast.LENGTH_SHORT)
                            .show()
                        val intent = Intent(this, WheelMainActivity::class.java) // 상세페이지 만들면 거기로 연결
                        startActivity(intent)
                    } else { // 휠체어등록에 실패한 경우
                        Toast.makeText(applicationContext, wheelName + " - 휠체어 등록에 실패하였습니다.", Toast.LENGTH_SHORT)
                            .show()
                        return@Listener
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            // 서버로 Volley를 이용해서 요청을 함.
            val WheelInsertRequest =
                WheelInsertRequest(wheelName, wheelDate, wheelRent, wheelBreak, responseListener)
            val queue = Volley.newRequestQueue(this)
            queue.add(WheelInsertRequest)
        })

        if (Build.VERSION.SDK_INT < 16) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }
}