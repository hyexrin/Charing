package com.hyexrin.chairing.user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hyexrin.chairing.MainActivity
import com.hyexrin.chairing.R
import kotlinx.android.synthetic.main.activity_user_main.*


class UserMainActivity : AppCompatActivity() {

    private lateinit var homeFragment: HomeFragment
    private lateinit var wheelchairFragment: WheelchairFragment
    private lateinit var myPageFragment: MypageFragment

    companion object {
        const val TAG: String = "로그"
    }

    // 메모리에 올라갔을 때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 레이아웃과 연결
        setContentView(R.layout.activity_user_main)

        Log.d(TAG, "UserMainActivity - onCreate() called")

        val intent = intent
        val userID = intent.getStringExtra("UserID")
        val userName = intent.getStringExtra("UserName")
        val userPass = intent.getStringExtra("UserPass")

        bottom_nav.setOnNavigationItemSelectedListener(onBottomNavItemSelectedListener)

        homeFragment = HomeFragment.newInstance()

        val subBundle = Bundle()
        val homeFragment = HomeFragment()
        subBundle.putString("userID", userID)
        subBundle.putString("userName", userName)
        subBundle.putInt("test", 5)
        homeFragment.arguments = subBundle

        supportFragmentManager.beginTransaction().replace(R.id.fragment_frame, homeFragment).commit()

    }

    // 바텀네비게이션 아이템 클릭 리스너 설정
    private val onBottomNavItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        // user 값 받아오기
        val intent = intent
        val userID = intent.getStringExtra("UserID")
        val userName = intent.getStringExtra("UserName")
        val userPass = intent.getStringExtra("UserPass")

        when(it.itemId) {
            R.id.menu_wheelchair -> {
                Log.d(TAG, "UserMainActivity - 휠체어 클릭")
                wheelchairFragment = WheelchairFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_frame, wheelchairFragment).commit()
            }
            R.id.menu_home -> {
                Log.d(TAG, "UserMainActivity - 홈 클릭")
                homeFragment = HomeFragment.newInstance()

                val subBundle = Bundle()
                val homeFragment = HomeFragment()
                subBundle.putString("userID", userID)
                subBundle.putString("userName", userName)
                subBundle.putInt("test", 5)
                homeFragment.arguments = subBundle

                supportFragmentManager.beginTransaction().replace(R.id.fragment_frame, homeFragment).commit()
            }
            R.id.menu_mypage -> {
                Log.d(TAG, "UserMainActivity - 마이페이지 클릭")
                myPageFragment = MypageFragment.newInstance()

                Log.d("user:", "$userID, $userName")

                val bundle = Bundle()
                val myPageFragment = MypageFragment()
                bundle.putString("userID", userID)
                bundle.putString("userName", userName)
                bundle.putInt("test", 1)
                myPageFragment.arguments = bundle

                supportFragmentManager.beginTransaction().replace(R.id.fragment_frame, myPageFragment).commit()

            }
        }
        true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@UserMainActivity, MainActivity::class.java) //지금 액티비티에서 다른 액티비티로 이동하는 인텐트 설정
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP //인텐트 플래그 설정
        startActivity(intent) //인텐트 이동
        finish() //현재 액티비티 종료
    }

    //    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        Log.d(TAG, "UserMainActivity - onNavigationItemSelected() called")
//
//        when(item.itemId) {
//            R.id.menu_wheelchair -> {
//                Log.d(TAG, "UserMainActivity - 휠체어 클릭")
//            }
//            R.id.menu_home -> {
//                Log.d(TAG, "UserMainActivity - 홈 클릭")
//            }
//            R.id.menu_mypage -> {
//                Log.d(TAG, "UserMainActivity - 마이페이지 클릭")
//            }
//        }
//
//        return true
//    }
}