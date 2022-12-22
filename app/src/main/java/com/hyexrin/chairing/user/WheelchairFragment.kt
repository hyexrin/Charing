package com.hyexrin.chairing.user

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hyexrin.chairing.R

class WheelchairFragment : Fragment() {

    companion object {
        const val TAG : String = "로그"

        fun newInstance() : WheelchairFragment {
            return WheelchairFragment()
        }
    }

    // 메모리에 올라갔을 때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "WheelchairFragment - onCreate() called")
    }

    // 프레그먼트를 안고 있는 액티비티에 붙었을 때
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "WheelchairFragment - onAttach() called")
    }

    // 뷰가 생성되었을 때
    // 프레그먼트와 레이아웃을 연결시켜주는 부분
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "WheelchairFragment - onCreateView() called")

        val view = inflater.inflate(R.layout.fragment_wheelchair, container, false)

        return view
    }
}