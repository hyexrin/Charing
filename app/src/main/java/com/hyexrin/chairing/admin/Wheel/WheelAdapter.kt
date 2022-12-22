package com.hyexrin.chairing.admin.Wheel

import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.hyexrin.chairing.R
import com.hyexrin.chairing.admin.Wheel.WheelRequest.WheelDeleteRequest
import kotlinx.android.synthetic.main.activity_wheel_insert.*
import org.json.JSONException
import org.json.JSONObject

// 리사이클러뷰 기능 어댑터
class WheelAdapter(val WheelList: ArrayList<Wheel>): RecyclerView.Adapter<WheelAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WheelAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_wheel, parent, false)

    //        return CustomViewHolder(view).apply {
//            itemView.setOnClickListener {
//                val curPos: Int = adapterPosition
//                val wheel: Wheel = WheelList.get(curPos)
//                Toast.makeText(parent.context, "휠체어 코드: ${wheel.WheelCode}\n휠체어 이름: ${wheel.WheelName}", Toast.LENGTH_SHORT).show()
//            }
        return CustomViewHolder(view).apply {
            wheelDel.setOnClickListener {
                val curPos: Int = adapterPosition
                val wheel: Wheel = WheelList.get(curPos)
                val wc = wheel.WheelName.toString()
                    val responseListener: Response.Listener<String?> = Response.Listener { response ->
                        try {
                            val jsonObject = JSONObject(response)
                            val success = jsonObject.getBoolean("success")
                            if (success) { // 휠체어삭제에 성공한 경우
                                WheelList.removeAt(curPos)
                                notifyDataSetChanged()
                                Toast.makeText(parent.context, "${wheel.WheelCode} - 휠체어를 삭제하였습니다.", Toast.LENGTH_SHORT).show()
                            } else { // 휠체어삭제에 실패한 경우
                                Toast.makeText(parent.context, " ${wheel.WheelCode} - 휠체어 삭제에 실패하였습니다.", Toast.LENGTH_SHORT)
                                        .show()
                                return@Listener
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                    // 서버로 Volley를 이용해서 요청을 함.
                    val WheelDeleteRequest =
                            WheelDeleteRequest(wc, responseListener)
                Log.d("wheelList - Del", wc)
                print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + wheelCode.toString() + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
                    val queue = Volley.newRequestQueue(parent.context)
                    queue.add(WheelDeleteRequest)
                }

            itemView.setOnClickListener {
                val curPos: Int = adapterPosition
                val wheel: Wheel = WheelList.get(curPos)
                val wc = wheel.WheelCode.toString()

                Log.d("wheelList - Click", wc)
                Toast.makeText(parent.context, "휠체어 코드: ${wheel.WheelCode}\n휠체어 이름: ${wheel.WheelName}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBindViewHolder(holder: WheelAdapter.CustomViewHolder, position: Int) {
        holder.wheelCode.text = WheelList.get(position).WheelCode.toString()
        holder.wheelName.text = WheelList.get(position).WheelName
        holder.wheelDate.text = WheelList.get(position).WheelDate
        holder.wheelRent.text = WheelList.get(position).WheelRent
        holder.wheelBreak.text = WheelList.get(position).WheelBreak
    }

    override fun getItemCount(): Int {
        return WheelList.size
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wheelCode = itemView.findViewById<TextView>(R.id.tvWheelCode)
        val wheelName = itemView.findViewById<TextView>(R.id.tvWheelName)
        val wheelDate = itemView.findViewById<TextView>(R.id.tvWheelDate)
        val wheelRent = itemView.findViewById<TextView>(R.id.tvWheelRent)
        val wheelBreak = itemView.findViewById<TextView>(R.id.tvWheelBreak)
        val wheelDel = itemView.findViewById<Button>(R.id.wheelDel)
    }
}


// 리스트뷰 기능 어댑터
//class WheelAdapter (val context: Context, val WheelList: ArrayList<Wheel>) : BaseAdapter(){
//    override fun getCount(): Int {
//        return WheelList.size
//    }
//
//    override fun getItem(position: Int): Any {
//        return WheelList[position]
//    }
//
//    override fun getItemId(position: Int): Long {
//        return 0
//    }
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_wheel, null)
//
//        val wheelCode = view.findViewById<TextView>(R.id.tvWheelCode)
//        val wheelName = view.findViewById<TextView>(R.id.tvWheelName)
//        val wheelDate = view.findViewById<TextView>(R.id.tvWheelDate)
//        val wheelRent = view.findViewById<TextView>(R.id.tvWheelRent)
//        val wheelBreak = view.findViewById<TextView>(R.id.tvWheelBreak)
//
//        val wheel = WheelList[position]
//
//        wheelCode.text = wheel.WheelCode.toString()
//        wheelName.text = wheel.WheelName
//        wheelDate.text = wheel.WheelDate
//        wheelRent.text = wheel.WheelRent
//        wheelBreak.text = wheel.WheelBreak
//
//        return view
//    }
//
//}