package com.hyexrin.chairing.user.service.rent

import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import java.util.*

class RentRequest(
//    number: Int,
//    rentTime: String,
//    returnTime: String,
    user: String,
    wheelCode: Int,
    rentPlace: Int,
    returnPlace: Int,
    time: String,
    how: Int,
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
        private const val URL = "http://cs2021mit.dongyangmirae.kr/Rent_mit.php"
    }

    init {
        map = HashMap()
//        map["number"] = number.toString() + ""
//        map["rentTime"] = rentTime
//        map["returnTime"] = returnTime
        map["user"] = user
        map["wheelCode"] = wheelCode.toString() + ""
        map["rentPlace"] = rentPlace.toString() + ""
        map["returnPlace"] = returnPlace.toString() + ""
        map["time"] = time
        map["how"] = how.toString() + ""
    }
}