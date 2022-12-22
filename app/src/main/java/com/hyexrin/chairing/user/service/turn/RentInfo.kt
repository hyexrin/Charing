package com.hyexrin.chairing.user.service.turn

import java.io.Serializable

class RentInfo  (val number: Int, val rentTime: String, val returnTime: String, val wheelCode: Int) : Serializable {

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