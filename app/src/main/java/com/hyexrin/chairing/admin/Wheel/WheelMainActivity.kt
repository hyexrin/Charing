package com.hyexrin.chairing.admin.Wheel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.RequestQueue
import com.hyexrin.chairing.R
import kotlinx.android.synthetic.main.activity_wheel_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Serializable
import java.net.HttpURLConnection
import java.net.URL

class WheelMainActivity : AppCompatActivity() {

    companion object{
        lateinit var requestQueue: RequestQueue
    }

    var wheelList: ArrayList<Wheel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("WheelMain - onCreate()", "onCreate() called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wheel_main)

        btnWheelInsert.setOnClickListener {
            val intent = Intent(this, WheelInsertActivity::class.java)
            startActivity(intent)
        }
        btnWheelSearch.setOnClickListener {
            val intent = Intent(this, WheelSearchActivity::class.java)
            startActivity(intent)
        }
        btnWheelList.setOnClickListener {
            //코루틴 영역 선언########################################################################################################################
            GlobalScope.launch {
                BackgroundTask()
            }
        }

    }

    /*onPreExecute, onProgressUpdate, onPostExecute, doInBackground 메소드를 한 메소드로 통합 ########################################################*/
    private suspend fun BackgroundTask() : Boolean {
        var target: String? = "http://cs2021mit.dongyangmirae.kr/WheelList.php"

        Log.d("WheelMain - doInBackground", "doInBackground() 실행")
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
            Log.d("WheelMain - parsing", "parsing() 완료")

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

            Log.d("WheelMain - onPostExecute", "onPostExecute() 실행")

        //21-10-19 주석
//            val intent = Intent(this@WheelMainActivity, WheelListActivity::class.java)
////            intent.putExtra("wheelList", stringBuffer.toString())
//            intent.putExtra("wheelList", wheelList)
//
////            val bundle = Bundle()
////            bundle.putSerializable("wheelList", wheelList)
////            Log.d("WheelMain - postWheelList", wheelList.toString())
//            startActivity(intent)

        return true
    }

    private fun parsing(data: String?) {
        Log.d("WheelMain - parsing", "parsing() 함수 실행")

        wheelList = ArrayList<Wheel>()
        try {
            Log.d("WheelMain - success", "parsing() try success")
            val jsonObject = JSONObject(data)
            val jsonArray = JSONArray(jsonObject.getString("response"))

            wheelList!!.clear()
            for (i in 0 until jsonArray.length()) {
                val jsonObject1= jsonArray[i] as JSONObject
                val wheel = Wheel(jsonObject1.getInt("code"), jsonObject1.getString("name"), jsonObject1.getString("date"), jsonObject1.getString("rent"), jsonObject1.getString("break"))
                wheelList!!.add(wheel)
                Log.d("WheelMain - wheelData", jsonObject1.getInt("code").toString() + jsonObject1.getString("name"))
            }
            Log.d("WheelMain - jsonLength", jsonArray.length().toString())
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("WheelMain - error", "parsing() error")
        }


        val intent = Intent(this@WheelMainActivity, WheelListActivity::class.java)
//            intent.putExtra("wheelList", stringBuffer.toString())
        intent.putExtra("wheelList", wheelList as Serializable)

//            val bundle = Bundle()
//            bundle.putSerializable("wheelList", wheelList)
//            Log.d("WheelMain - postWheelList", wheelList.toString())
        startActivity(intent)

    }
}