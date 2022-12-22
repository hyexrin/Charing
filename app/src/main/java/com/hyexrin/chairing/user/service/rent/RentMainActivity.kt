package com.hyexrin.chairing.user.service.rent

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.hyexrin.chairing.R
import com.hyexrin.chairing.user.UserMainActivity
import kotlinx.android.synthetic.main.activity_now_rent_sub.*
import kotlinx.android.synthetic.main.activity_rent_main.*
import org.json.JSONException
import org.json.JSONObject


class RentMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rent_main)

        // user 값 받아오기
        val intent = intent
        val userID = intent.getStringExtra("UserID")
        val userName = intent.getStringExtra("UserName")
        val userPass = intent.getStringExtra("UserPass")

        Log.d("user-Rent:", "$userID, $userName")

//        txtGoInfo.setOnClickListener {
//            Toast.makeText(this, "${userID}님, Hello", Toast.LENGTH_SHORT).show()
//        }

        println("@@@@@@@@@@@@@@go Rent page@@@@@@@@@@@@@@@")

        var imgMain = findViewById<ImageView>(R.id.imgMain)
        var imgPre = findViewById<ImageView>(R.id.imgPre)

        // 상단 이미지 버튼 클릭 시 수행
        imgMain.setOnClickListener {
            val intent = Intent(this@RentMainActivity, UserMainActivity::class.java)
            intent.putExtra("UserID", userID)
            intent.putExtra("UserPass", userPass)
            intent.putExtra("UserName", userName)
            startActivity(intent)
            finish()
        }
        imgPre.setOnClickListener {
            val intent = Intent(this@RentMainActivity, UserMainActivity::class.java)
            intent.putExtra("UserID", userID)
            intent.putExtra("UserPass", userPass)
            intent.putExtra("UserName", userName)
            startActivity(intent)
            finish()
        }


//        // 즉시 대여 페이지 이동
//        btnNowRent.setOnClickListener{
//            val intent = Intent(this@RentMainActivity, NowRentActivity::class.java)
//            intent.putExtra("UserID", userID)
//            intent.putExtra("UserName", userName)
//            startActivity(intent)
//        }
        // 대여 신청 페이지 이동
//        btnReservationRent.setOnClickListener{
//            val intent = Intent(this@RentMainActivity, ReservationActivity::class.java)
//            intent.putExtra("UserID", userID)
//            intent.putExtra("UserName", userName)
//            startActivity(intent)
//        }
        // 휠체어 부르기 페이지 이동
        btnCallRent.setOnClickListener{
            val intent = Intent(this@RentMainActivity, CallActivity::class.java)
            intent.putExtra("UserID", userID)
            intent.putExtra("UserName", userName)
            startActivity(intent)
        }
    }

    fun startBarcodeReaderCustomActivity(view: View) {
        val integrator = IntentIntegrator(this)
        integrator.setBarcodeImageEnabled(true)
        integrator.captureActivity = NowRentActivity::class.java
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode,resultCode, data)
//        val rentCode = findViewById<TextView>(R.id.rentCode)

        val intent = intent
        val userID = intent.getStringExtra("UserID")
        val userName = intent.getStringExtra("UserName")

        if (result != null) {
            if(result.contents != null) {
                val intent = Intent(this@RentMainActivity, NowRentSubActivity::class.java)

                intent.putExtra("UserID", userID)
                intent.putExtra("UserName", userName)


                Toast.makeText(this, "scanned: ${result.contents} format: ${result.formatName}", Toast.LENGTH_LONG).show()
                try {
                    //data를 json으로 변환
                    val obj = JSONObject(result.contents)
                    val objC = obj.getString("name")
                    intent.putExtra("code", objC)
//                    rentCode.setText(obj.getString("name"))
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            }
//            if (result.barcodeImagePath != null ) {
//                val bitmap = BitmapFactory.decodeFile(result.barcodeImagePath)
//                val scannedBitmap: ImageView = findViewById(R.id.scannedBitmap)
//                scannedBitmap.setImageBitmap(bitmap)
//            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()

        // user 값 받아오기
        val intent = intent
        val userID = intent.getStringExtra("UserID")
        val userName = intent.getStringExtra("UserName")
        val userPass = intent.getStringExtra("UserPass")

        val subIntent = Intent(this@RentMainActivity, UserMainActivity::class.java) //지금 액티비티에서 다른 액티비티로 이동하는 인텐트 설정
        subIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP //인텐트 플래그 설정

        subIntent.putExtra("UserID", userID)
        subIntent.putExtra("UserPass", userPass)
        subIntent.putExtra("UserName", userName)
        startActivity(subIntent)
        finish()

    }



}