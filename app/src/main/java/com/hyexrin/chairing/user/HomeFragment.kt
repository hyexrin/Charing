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
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.hyexrin.chairing.LoginActivity
import com.hyexrin.chairing.R
import com.hyexrin.chairing.user.service.rent.RentMainActivity
import com.hyexrin.chairing.user.service.turn.RentInfo
import com.hyexrin.chairing.user.service.turn.ReturnMainActivity
import com.hyexrin.chairing.user.service.turn.returnRequest.ReturnRequest
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Serializable
import java.net.HttpURLConnection
import java.net.URL

class HomeFragment : Fragment() {
    val userID = arguments?.getString("userID")

    companion object {
        const val TAG : String = "로그"

        fun newInstance() : HomeFragment {
            return HomeFragment()
        }
    }

    var RentList: ArrayList<RentInfo>? = null

    // 메모리에 올라갔을 때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "HomeFragment - onCreate() called")
    }

    // 프레그먼트를 안고 있는 액티비티에 붙었을 때
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "HomeFragment - onAttach() called")
    }

    // 뷰가 생성되었을 때
    // 프레그먼트와 레이아웃을 연결시켜주는 부분
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "HomeFragment - onCreateView() called")

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(TAG, "HomeFragment - onActivityCreated() called")

        val bundle = arguments
        val userID = arguments?.getString("userID")
        val userName = arguments?.getString("userName")
        val test = bundle?.getInt("test")

        Log.d("UserID(Home)-->", "$userID")
        Log.d("UserName-->", "$userName")
        Log.d("UserTest-->", "$test")

        // 대여 신청 페이지로 이동
        btnRent.setOnClickListener{

            val intent = Intent(getActivity(), RentMainActivity::class.java)
            intent.putExtra("UserID", userID)
            intent.putExtra("UserName", userName)
            startActivity(intent)

        }
        // 반납 신청 페이지로 이동 - 211019 주석
//        btnReturn.setOnClickListener{
//            Log.d("Home - btnReturn", "반납신청 버튼 클릭1")
//            val intent = Intent(getActivity(), ReturnMainActivity::class.java)
//            startActivity(intent)
//        }

        super.onActivityCreated(savedInstanceState)

        // RentAdapter

        btnReturn.setOnClickListener {
            Log.d("home - btnReturn", "반납신청 버튼 클릭2")
            GlobalScope.launch {
                BackgroundTask()
            }
        }
    }
    /*onPreExecute, onProgressUpdate, onPostExecute, doInBackground 메소드를 한 메소드로 통합 ########################################################*/
    private suspend fun BackgroundTask() : Boolean {
        var target: String? = "http://cs2021mit.dongyangmirae.kr/Mem_RentList_mit.php"

        Log.d("home - doInBackground", "doInBackground() 실행")
        var `is`: InputStream? = null
        val isr: InputStreamReader? = null
        var reader: BufferedReader? = null
        val stringBuffer = StringBuffer()
        try {
            val url = URL(target)
            val httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.connectTimeout = 10000
            if (httpURLConnection.responseCode == HttpURLConnection.HTTP_OK) {
                `is` = httpURLConnection.inputStream
                reader = BufferedReader(InputStreamReader(`is`))
                while (true) {
                    val stringLine = reader.readLine() ?: break
                    stringBuffer.append(
                            """
                            $stringLine
                            
                            """.trimIndent()
                    )
                }
            }
            parsing(stringBuffer.toString())
            Log.d("home - parsing", "parsing() 완료")
            user(userID)

//                val intent = Intent(this@WheelMainActivity, WheelListActivity::class.java)
//                intent.putExtra("wheelList", result)
//
//                val bundle = Bundle()
//                bundle.putSerializable("wheelList", wheelList)
//                Log.d("WheelMain - postWheelList", wheelList.toString())
//                startActivity(intent)

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                reader?.close()
                isr?.close()
                `is`?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        Log.d("home - onPostExecute", "onPostExecute() 실행")

        val intent = Intent(getActivity(), ReturnMainActivity::class.java)
//            intent.putExtra("wheelList", stringBuffer.toString())
        intent.putExtra("RentList", RentList)
        Log.d("home - putExtra", "intent 값 넘기기 성공 ${RentList.toString()}")

//            val bundle = Bundle()
//            bundle.putSerializable("wheelList", wheelList)
//            Log.d("WheelMain - postWheelList", wheelList.toString())
        startActivity(intent)

        return true
    }

    private fun parsing(data: String?) {
        Log.d("home - parsing", "parsing() 함수 실행")

        RentList = ArrayList<RentInfo>()
        try {
            Log.d("home - success", "parsing() try success")
            val jsonObject = JSONObject(data)
            val jsonArray = JSONArray(jsonObject.getString("response"))

            RentList!!.clear()
            for (i in 0 until jsonArray.length()) {
                val jsonObject1= jsonArray[i] as JSONObject
                val rent = RentInfo(jsonObject1.getInt("number"), jsonObject1.getString("rentTime"), jsonObject1.getString("returnTime"), jsonObject1.getInt("wheelCode"))
                RentList!!.add(rent)
                Log.d("home - RentData", "number: ${jsonObject1.getInt("number")}, rentTime: ${jsonObject1.getString("rentTime")}  -> wheelCode:" + jsonObject1.getInt("wheelCode"))
            }
            Log.d("home - jsonLength", jsonArray.length().toString())
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("home - error", "parsing() error")
        }


        val intent = Intent(getActivity(), ReturnMainActivity::class.java)
//            intent.putExtra("wheelList", stringBuffer.toString())
        intent.putExtra("RentList", RentList as Serializable)

//            val bundle = Bundle()
//            bundle.putSerializable("wheelList", wheelList)
//            Log.d("WheelMain - postWheelList", wheelList.toString())
        startActivity(intent)

    }

    private fun user(data: String?) {
        btnJoin.setOnClickListener(View.OnClickListener { // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
            val userID = data.toString()

            val responseListener: Response.Listener<String?> = Response.Listener { response ->
                try {
                    val jsonObject = JSONObject(response)
                    val success = jsonObject.getBoolean("success")
                    if (success) { // 회원등록에 성공한 경우
                        Toast.makeText(context, "회원 등록에 성공하였습니다.", Toast.LENGTH_SHORT)
                                .show()
                        val intent = Intent(context, LoginActivity::class.java)
                        startActivity(intent)
                    } else { // 회원등록에 실패한 경우
                        Toast.makeText(context, "회원 등록에 실패하였습니다.", Toast.LENGTH_SHORT)
                                .show()
                        return@Listener
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            // 서버로 Volley를 이용해서 요청을 함.
            val ReturnRequest =
                    ReturnRequest(userID, responseListener)
            val queue = Volley.newRequestQueue(context)
            queue.add(ReturnRequest)
        })

    }
}