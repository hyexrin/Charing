package com.hyexrin.chairing

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.hyexrin.chairing.user.request.RegisterRequest
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.etId
import org.json.JSONException
import org.json.JSONObject
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { // 액티비티 시작시 처음으로 실행되는 생명주기!
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        // 회원가입 버튼 클릭 시 수행
        btnJoin.setOnClickListener(View.OnClickListener { // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
            val userID = etId.getText().toString()
            val userPass = etPw1.getText().toString()
            val userName = etName.getText().toString()
            val userAddress = etAddress.getText().toString()
            val userPhone = etPhone.getText().toString()
            val userProtector = etProtector.getText().toString()
            val userBirth = etBirth.getText().toString().toInt()
            val responseListener: Response.Listener<String?> = Response.Listener { response ->
                try {
                    val jsonObject = JSONObject(response)
                    val success = jsonObject.getBoolean("success")
                    if (success) { // 회원등록에 성공한 경우
                        Toast.makeText(applicationContext, "회원 등록에 성공하였습니다.", Toast.LENGTH_SHORT)
                            .show()
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        startActivity(intent)
                    } else { // 회원등록에 실패한 경우
                        Toast.makeText(applicationContext, "회원 등록에 실패하였습니다.", Toast.LENGTH_SHORT)
                            .show()
                        return@Listener
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            // 서버로 Volley를 이용해서 요청을 함.
            val registerRequest =
                RegisterRequest(userID, userPass, userName, userAddress, userPhone, userProtector, userBirth, responseListener)
            val queue = Volley.newRequestQueue(this@RegisterActivity)
            queue.add(registerRequest)
        })

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

        // 회원가입 정규식
        val pwValidation = "^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#\$%^&*])(?=.*[0-9!@#\$%^&*]).{8,15}\$"
        val emailValidation ="^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}\$"
        val phoneValidation = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})\$"
        val birthValidation = "^([0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1,2][0-9]|3[0,1]))\$"


        edtCheck(testId, etId, "이메일 형식으로 입력해주세요", emailValidation)
        edtCheck(testPw1, etPw1, "비밀번호 형식을 확인해주세요", pwValidation)
        edtCheck(testPhone, etPhone, "'-' 없이 숫자만 입력해주세요.", phoneValidation)
        edtCheck(testBirth, etBirth, "YYMMDD 형식으로 입력해주세요.", birthValidation)


        // pw2
        etPw2.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                testPw2.setText("")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                testPw2.setText("")
            }

            override fun afterTextChanged(s: Editable?) {
                if(etPw1.getText().toString().equals(etPw2.getText().toString())){
                    testPw2.setText("")
                }
                else
                    testPw2.setText("입력하신 비밀번호를 확인해주세요.")
            }
        })
    }

    // 정규화 함수
    private fun edtCheck(chText : TextView, chBox: EditText, txt: String, validation: String) {

        chBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                chText.setText("")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                chText.setText("")
            }

            override fun afterTextChanged(s: Editable?) {
                if(!Pattern.matches(validation, s.toString()))
                {
                    chText.setText(txt)
                    chBox.setBackgroundResource(R.drawable.red_edittext)
                } else{
                    chText.setText("")
                    chBox.setBackgroundResource(R.drawable.white_edittext)
                }
            }
        })
    }
}