package com.hyexrin.chairing.user.service.turn.returnRequest

import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import java.util.*

class ReturnListRequest(number: Int, listener: Response.Listener<String?>?) : StringRequest(Method.POST, URL, listener, null) {
    private val parameters: MutableMap<String, String>
    public override fun getParams(): Map<String, String> {
        return parameters
    }

    companion object {
        private const val URL = "http://cs2021mit.dongyangmirae.kr/ReturnWheel_mit.php"
    }

    init {
        parameters = HashMap()
        parameters["number"] = number.toString() + ""
    }
}