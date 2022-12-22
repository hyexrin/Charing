package com.hyexrin.chairing.admin.Wheel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyexrin.chairing.R
import kotlinx.android.synthetic.main.activity_wheel_list.*

class   WheelListActivity : AppCompatActivity() {
//
//    var WheelList = arrayListOf<Wheel>(
//        Wheel(1,"abc210716-001","2021-07-16", "F", "F"),
//        Wheel(2,"abc210716-002","2021-07-16", "F", "F"),
//        Wheel(3,"abc210716-003","2021-07-16", "F", "F"),
//        Wheel(4,"abc210716-004","2021-07-16", "F", "F"),
//        Wheel(5,"abc210716-005","2021-07-16", "F", "F"),
//        Wheel(6,"abc210716-006","2021-07-16", "F", "F"),
//        Wheel(7,"abc210716-007","2021-07-16", "F", "F"),
//        Wheel(8,"abc210716-008","2021-07-16", "F", "F"),
//        Wheel(9,"abc210716-009","2021-07-16", "F", "F")
//
//    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wheel_list)


    val intent = intent
//    val wheelListStr = intent.getStringExtra("wheelList")
    val wheelList = intent.getSerializableExtra("wheelList")
    Log.d("ReturnMain Test", wheelList.toString())

    //Log.d("WheelMain - postWheelList2323", wheelList)

//    if(wheelList!=null){
//        Toast.makeText(this, "${wheelListStr} 값 반환 성공", Toast.LENGTH_SHORT).show()
//        print("@@@wheelList = ${wheelList}")
//        tvWheelList.setText(wheelListStr)
//    } else {
//        tvWheelList.setText("null 반환")
//        Toast.makeText(this, "${wheelListStr} 값 반환 실패", Toast.LENGTH_SHORT).show()
//        print("wheelList null")
//    }

        rvWheel.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvWheel.setHasFixedSize(true)

        rvWheel.adapter = WheelAdapter(wheelList as ArrayList<Wheel>)


        // 리사이클러뷰 적용
//        val intent = intent
//        if (intent != null) {
//            val wheelBundle = intent.getBundleExtra("wheelList")
//            val wheelList: ArrayList<Wheel>? = wheelBundle["wheelList"] as ArrayList<Wheel>?
//
//            val builder = StringBuilder()
//            for (dto in wheelList!!) {
//                builder.append(
//                    """
//                        휠체어 코드 : ${dto.WheelCode.toInt()}
//
//                        """.trimIndent()
//                )
//                builder.append(
//                    """
//                        휠체어 이름 : ${dto.WheelName.toString()}
//
//                        """.trimIndent()
//                )
//                builder.append(
//                    """
//                        휠체어 등록 날짜 : ${dto.WheelDate.toString()}
//
//                        """.trimIndent()
//                )
//                builder.append(
//                    """
//                        휠체어 대여 상태 : ${dto.WheelRent.toString()}
//
//                        """.trimIndent()
//                )
//                builder.append(
//                    """
//                        휠체어 고장 상태 : ${dto.WheelBreak.toString()}
//
//                        """.trimIndent()
//                )
//                builder.append("--------------------------------------\n\n\n")
//            }
//            tvWheelList!!.text = builder.toString()
//        } else {
//            tvWheelList.setText("wheel Null 반환")
//        }




// 리스트뷰
////        val item = arrayOf("사과", "배", "수박", "참외", "메론")
////        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, item)
//
//        val adapter = WheelAdapter(this, WheelList)
//        listView.adapter = adapter
//
//        // 해당 리스트 클릭이벤트
//        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
//            val selectItem = parent.getItemAtPosition(position) as Wheel
//            Toast.makeText(this, selectItem.WheelName, Toast.LENGTH_SHORT).show()
//        }
    }


    override fun onBackPressed() {
        super.onBackPressed()

        // user 값 받아오기
//        val intent = intent
//        val userID = intent.getStringExtra("UserID")
//        val userName = intent.getStringExtra("UserName")
//        val userPass = intent.getStringExtra("UserPass")

        val subIntent = Intent(this@WheelListActivity, WheelMainActivity::class.java) //지금 액티비티에서 다른 액티비티로 이동하는 인텐트 설정
        subIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP //인텐트 플래그 설정

//        subIntent.putExtra("UserID", userID)
//        subIntent.putExtra("UserPass", userPass)
//        subIntent.putExtra("UserName", userName)
        startActivity(subIntent)
        finish()

    }

}