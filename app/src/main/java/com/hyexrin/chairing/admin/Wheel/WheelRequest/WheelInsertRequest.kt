package com.hyexrin.chairing.admin.Wheel.WheelRequest

import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import java.util.*

class WheelInsertRequest(
//    WheelCode: Int,
    WheelName: String,
    WheelDate: String,
    WheelRent: String,
    WheelBreak: String,
    listener: Response.Listener<String?>?
) : StringRequest(
    Method.POST, URL, listener, null
) {
    private val map: MutableMap<String, String>
    @Throws(AuthFailureError::class)
    override fun getParams(): Map<String, String> {
        return map
    }

    companion object {
        // 서버 URL 설정 ( PHP 파일 연동 )
        private const val URL = "http://cs2021mit.dongyangmirae.kr/WheelInsert_mit.php"
    }

    init {
        map = HashMap()
//        map["code"] =  WheelCode.toString() + ""
        map["name"] = WheelName
        map["date"] = WheelDate
        map["rent"] = WheelRent
        map["break"] = WheelBreak
    }
}