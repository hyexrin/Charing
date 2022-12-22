package com.hyexrin.chairing

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_inform.*

class InformActivity : AppCompatActivity() {
    companion object {
        const val TAG: String = "로그"
    }

    private var pageItemList = ArrayList<PageItem>()
    private lateinit var myIntroPagerRecyclerAdapter: MyInformPagerRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inform)


        previous_btn.setOnClickListener {
            Log.d(TAG, "MainActivity - 이전 버튼 클릭")

            my_intro_view_pager.currentItem = my_intro_view_pager.currentItem - 1
        }

        next_btn.setOnClickListener {
            Log.d(TAG, "MainActivity - 다음 버튼 클릭")
            my_intro_view_pager.currentItem = my_intro_view_pager.currentItem + 1
        }

        pageItemList.add(PageItem(R.color.colorBg_main, R.drawable.ic_inform_1, "1. 안녕하세요. 반가워요!"))
        pageItemList.add(PageItem(R.color.colorBg_main, R.drawable.ic_inform_2, "2. 로그인 / 회원가입을 해주세요!"))
        pageItemList.add(PageItem(R.color.colorBg_main, R.drawable.ic_inform_3, "3. 근처에 있는 휠체어를\n대여/반납 해보세요!"))

        myIntroPagerRecyclerAdapter = MyInformPagerRecyclerAdapter(pageItemList)

        if (Build.VERSION.SDK_INT < 16) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        actionBar?.hide()

        // 뷰페이저에 설정
        my_intro_view_pager.apply {

            adapter = myIntroPagerRecyclerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL

//            this.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//
//                override fun onPageSelected(position: Int) {
//                    super.onPageSelected(position)
////                    supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.colorBlue)))
//                }
//
//            })

            dots_indicator.setViewPager2(this)
        }

    }


}
