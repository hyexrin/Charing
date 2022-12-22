package com.hyexrin.chairing

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.hyexrin.chairing.user.UserMainActivity
import com.hyexrin.chairing.user.request.LoginRequest
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.exception.KakaoException
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_mypage.*
import org.json.JSONException
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private var mSessionCallback: ISessionCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 상단 이미지 버튼 클릭 시 수행
        imgMain.setOnClickListener {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }
        imgPre.setOnClickListener {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }

        // 회원가입 버튼을 클릭 시 수행
        btnRegister.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        })

        //////////
        // SharedPreferences 안에 값이 저장되어 있지 않을 때 -> Login
        if(MySharedPreferences.getUserId(this).isNullOrBlank()
                || MySharedPreferences.getUserPass(this).isNullOrBlank()) {
            Login()
        }
        else { // SharedPreferences 안에 값이 저장되어 있을 때 -> UserMainActivity로 이동
            Toast.makeText(this, "${MySharedPreferences.getUserId(this)}님 자동 로그인이 되었습니다.", Toast.LENGTH_SHORT).show()

            val UserID = MySharedPreferences.getUserId(this)
            val UserPass = MySharedPreferences.getUserPass(this)
            val responseListener = Response.Listener<String?> { response ->
                try {
                    // TODO : 인코딩 문제때문에 한글 DB인 경우 로그인 불가
                    println("result:$response")
                    val jsonObject = JSONObject(response)
                    val success = jsonObject.getBoolean("success")
                    if (success) { // 로그인에 성공한 경우
                        val UserID = jsonObject.getString("id")
                        val UserPass = jsonObject.getString("pwd")
                        val UserName = jsonObject.getString("name")

                        val intent = Intent(this@LoginActivity, UserMainActivity::class.java)
                        intent.putExtra("UserID", UserID)
                        intent.putExtra("UserPass", UserPass)
                        intent.putExtra("UserName", UserName)
                        startActivity(intent)

                    } else { // 로그인에 실패한 경우
                        Toast.makeText(applicationContext, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT)
                                .show()
                        return@Listener
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            val loginRequest = LoginRequest(UserID, UserPass, responseListener)
            val queue = Volley.newRequestQueue(this@LoginActivity)
            queue.add(loginRequest)

            val intent = Intent(this, UserMainActivity::class.java)
            startActivity(intent)
            finish()
        }


        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        actionBar?.hide()


        mSessionCallback = object : ISessionCallback {
            override fun onSessionOpened() {
                // 로그인 요청
                UserManagement.getInstance().me(object : MeV2ResponseCallback() {
                    override fun onFailure(errorResult: ErrorResult) {
                        // 로그인 실패
                        Toast.makeText(this@LoginActivity, "로그인 도중에 오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
                    }

                    override fun onSessionClosed(errorResult: ErrorResult) {
                        // 세션이 닫힘..
                        Toast.makeText(this@LoginActivity, "세션이 닫혔습니다.. 다시 시도해주세요", Toast.LENGTH_SHORT).show()
                    }

                    override fun onSuccess(result: MeV2Response) {
                        // 로그인 성공
                        val intent = Intent(this@LoginActivity, UserMainActivity::class.java)
                        intent.putExtra("UserName", result.kakaoAccount.profile.nickname)
                        //intent.putExtra("profileImg", result.kakaoAccount.profile.profileImageUrl)
                        intent.putExtra("UserID", result.kakaoAccount.email)
                        startActivity(intent)
                        Toast.makeText(this@LoginActivity, result.kakaoAccount.profile.nickname + "카카오 로그인 -- 환영 합니다 !", Toast.LENGTH_SHORT).show()
                    }
                })
            }

            override fun onSessionOpenFailed(exception: KakaoException) {
                Toast.makeText(this@LoginActivity, "onSessionOpenFailed", Toast.LENGTH_SHORT).show()
            }
        }
        Session.getCurrentSession().addCallback(mSessionCallback)
        Session.getCurrentSession().checkAndImplicitOpen()

    }

    fun Login() {

        // 로그인 버튼을 클릭 시 수행
        btnLogin.setOnClickListener(View.OnClickListener { // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
            val UserID = etId.getText().toString()
            val UserPass = etPw.getText().toString()
            val responseListener = Response.Listener<String?> { response ->
                try {
                    // TODO : 인코딩 문제때문에 한글 DB인 경우 로그인 불가
                    println("result:$response")
                    val jsonObject = JSONObject(response)
                    val success = jsonObject.getBoolean("success")
                    if (success) { // 로그인에 성공한 경우
                        val UserID = jsonObject.getString("id")
                        val UserPass = jsonObject.getString("pwd")
                        val UserName = jsonObject.getString("name")

                        MySharedPreferences.setUserId(this, etId.text.toString())
                        MySharedPreferences.setUserPass(this, etPw.text.toString())

                        Toast.makeText(applicationContext, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT)
                                .show()
                        val intent = Intent(this@LoginActivity, UserMainActivity::class.java)
                        intent.putExtra("UserID", UserID)
                        intent.putExtra("UserPass", UserPass)
                        intent.putExtra("UserName", UserName)
                        startActivity(intent)

                    } else { // 로그인에 실패한 경우
                        Toast.makeText(applicationContext, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT)
                                .show()
                        return@Listener
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            val loginRequest = LoginRequest(UserID, UserPass, responseListener)
            val queue = Volley.newRequestQueue(this@LoginActivity)
            queue.add(loginRequest)
        })
        if (Build.VERSION.SDK_INT < 16) {
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        super.onDestroy()
        Session.getCurrentSession().removeCallback(mSessionCallback)
    }

}