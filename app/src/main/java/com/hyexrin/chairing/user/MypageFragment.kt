package com.hyexrin.chairing.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hyexrin.chairing.InformActivity
import com.hyexrin.chairing.LoginActivity
import com.hyexrin.chairing.MySharedPreferences
import com.hyexrin.chairing.R
import com.hyexrin.chairing.admin.AdminMainActivity
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.LogoutResponseCallback
import kotlinx.android.synthetic.main.fragment_mypage.*


class MypageFragment : Fragment() {

    val userID = arguments?.getString("userID")

    companion object {
        const val TAG : String = "로그"

        fun newInstance() : MypageFragment {
            return MypageFragment()
        }
    }


    // 메모리에 올라갔을 때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "MypageFragment - onCreate() called")
    }

    // 프레그먼트를 안고 있는 액티비티에 붙었을 때
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "MypageFragment - onAttach() called")

    }

    // 뷰가 생성되었을 때
    // 프레그먼트와 레이아웃을 연결시켜주는 부분
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "MypageFragment - onCreateView() called")

        val view = inflater.inflate(R.layout.fragment_mypage, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(TAG, "MypageFragment - onActivityCreated() called")

        val bundle = arguments
        val userID = arguments?.getString("userID")
        val userName = arguments?.getString("userName")
        val test = bundle?.getInt("test")
        admin(userID)

        Log.d("UserID(Mypage)-->", "$userID")
        Log.d("UserName-->", "$userName")
        Log.d("UserTest-->", "$test")

        txtId.setText(userName+"님")

        // 로그아웃
        txtLogout.setOnClickListener {
            MySharedPreferences.clearUser(requireContext())
            Toast.makeText(context, "로그아웃 하였습니다.", Toast.LENGTH_SHORT).show()
            UserManagement.getInstance().requestLogout(object : LogoutResponseCallback() {
                override fun onCompleteLogout() {
                    // 로그아웃 성공시 수행하는 지점
                    val intent = Intent(getActivity(), LoginActivity::class.java)
                    startActivity(intent)
                }
            })
        }

        btnCharing.setOnClickListener {
            val InformIntent = Intent(context, InformActivity::class.java) // 인텐트를 생성
                startActivity(InformIntent)
        }

        btnGoAdmin.setOnClickListener {
            val intent = Intent(context, AdminMainActivity::class.java)
            startActivity(intent)
        }

        super.onActivityCreated(savedInstanceState)
    }

    private fun admin(data: String?){
        if(data != "admin"){
            btnGoAdmin.setVisibility(View.INVISIBLE)
        }
    }
}