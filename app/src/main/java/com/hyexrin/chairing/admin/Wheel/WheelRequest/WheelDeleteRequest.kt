package com.hyexrin.chairing.admin.Wheel.WheelRequest

import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import java.util.*

class WheelDeleteRequest(name: String, listener: Response.Listener<String?>?) : StringRequest(Method.POST, URL, listener, null) {
    private val parameters: MutableMap<String, String>
    public override fun getParams(): Map<String, String> {
        return parameters
    }

    companion object {
        private const val URL = "http://cs2021mit.dongyangmirae.kr/WheelDel_mit.php"
    }

    init {
        parameters = HashMap()
        parameters["name"] = name
    }
}