package com.hyexrin.chairing.user.service.turn

import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.hyexrin.chairing.R
import com.hyexrin.chairing.user.service.turn.returnRequest.ReturnListRequest
import kotlinx.android.synthetic.main.activity_wheel_insert.*
import org.json.JSONException
import org.json.JSONObject

// 리사이클러뷰 기능 어댑터
class ReturnAdapter(val RentList: ArrayList<RentInfo>): RecyclerView.Adapter<ReturnAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReturnAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_mem_rent, parent, false)

        return CustomViewHolder(view).apply {
            Log.d("ReturnAdapter - onCreate", "onCreateViewHolder called()")
            wheelReturn.setOnClickListener {
                Log.d("ReturnAdapter - wheelReturn", "wheelReturn btn click")
                val curPos: Int = adapterPosition
                val rentInfo: RentInfo = RentList.get(curPos)
                val rentNum = rentInfo.number
                    val responseListener: Response.Listener<String?> = Response.Listener { response ->
                        try {
                            Log.d("ReturnAdapter - json Try", "진입 성공")
                            val jsonObject = JSONObject(response)
                            val success = jsonObject.getBoolean("success")
                            if (success) { // 휠체어반납에 성공한 경우
                                RentList.removeAt(curPos)
                                notifyDataSetChanged()
                                Toast.makeText(parent.context, "${rentInfo.number} - 휠체어를 반납하였습니다.", Toast.LENGTH_SHORT).show()
                            } else { // 휠체어반납에 실패한 경우
                                Log.d("ReturnAdapter - json Try", "진입 실패")
                                Toast.makeText(parent.context, " ${rentInfo.number} - 휠체어 반납에 실패하였습니다.", Toast.LENGTH_SHORT)
                                        .show()
                                return@Listener
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                    // 서버로 Volley를 이용해서 요청을 함.
                    val returnListRequest =
                            ReturnListRequest(rentNum, responseListener)
                Log.d("RentList - update", rentNum.toString())
                print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + rentNum.toString() + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
                    val queue = Volley.newRequestQueue(parent.context)
                    queue.add(returnListRequest)
                }

            itemView.setOnClickListener {
                val curPos: Int = adapterPosition
                val RentInfo: RentInfo = RentList.get(curPos)
                val rentNum = RentInfo.number

                Log.d("RentList - Click", rentNum.toString())
                Toast.makeText(parent.context, "휠체어 대여 번호: ${RentInfo.number}\n휠체어 이름: ${RentInfo.wheelCode}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBindViewHolder(holder: ReturnAdapter.CustomViewHolder, position: Int) {
        Log.d("ReturnAdapter - onBindViewHolder", "called()")
        holder.number.text = RentList.get(position).number.toString()
        holder.rentTime.text = RentList.get(position).rentTime
        holder.returnTime.text = RentList.get(position).returnTime
        holder.wheelCode.text = RentList.get(position).wheelCode.toString()
    }

    override fun getItemCount(): Int {
        return RentList.size
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val number = itemView.findViewById<TextView>(R.id.tvListNum)
        val rentTime= itemView.findViewById<TextView>(R.id.tvListRent)
        val returnTime = itemView.findViewById<TextView>(R.id.tvListReturn)
        val wheelCode = itemView.findViewById<TextView>(R.id.tvListWheel)
        val wheelReturn = itemView.findViewById<Button>(R.id.wheelReturn)
    }
}
