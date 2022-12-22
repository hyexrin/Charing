package com.hyexrin.chairing.admin.Wheel

import java.io.Serializable

class Wheel  (val WheelCode: Int, val WheelName: String, val WheelDate: String, val WheelRent: String, val WheelBreak: String) : Serializable {

}
//
//
///**
// * Created by choi on 2017-05-08.
// */
//class Wheel : Serializable {
//    var WheelCode: Int? = null
//    var WheelName: String? = null
//    var WheelDate: String? = null
//    var WheelRent: String? = null
//    var WheelBreak: String? = null
//
//    constructor() {}
//    constructor(WheelCode: Int?, WheelName: String?, WheelDate: String?, WheelRent: String?, WheelBreak: String?) {
//        this.WheelCode = WheelCode
//        this.WheelName = WheelName
//        this.WheelDate = WheelDate
//        this.WheelRent = WheelRent
//        this.WheelBreak = WheelBreak
//    }
//
//    override fun toString(): String {
//        return "Wheel{" +
//                "WheelCode='" + WheelCode + '\'' +
//                ", WheelName='" + WheelName + '\'' +
//                ", WheelDate='" + WheelDate + '\'' +
//                ", WheelRent='" + WheelRent + '\'' +
//                ", WheelBreak=" + WheelBreak +
//                '}'
//    }
//
//    companion object {
//        private const val serialVersionUID = 1L
//    }
//}