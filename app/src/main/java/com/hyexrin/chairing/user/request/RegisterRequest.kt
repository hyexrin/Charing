package com.hyexrin.chairing.user.request

import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import java.util.*

class RegisterRequest(
    UserID: String,
    UserPassword: String,
    UserName: String,
    UserAddress: String,
    UserPhone: String,
    UserProtector: String,
    UserBirth: Int,
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
        private const val URL = "http://cs2021mit.dongyangmirae.kr/Register_mit.php"
    }

    init {
        map = HashMap()
        map["id"] = UserID
        map["pwd"] = UserPassword
        map["name"] = UserName
        map["address"] = UserAddress
        map["phone"] = UserPhone
        map["protector"] = UserProtector
        map["birth"] = UserBirth.toString() + ""
    }
}