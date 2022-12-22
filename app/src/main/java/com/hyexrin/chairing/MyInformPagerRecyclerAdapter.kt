package com.hyexrin.chairing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyInformPagerRecyclerAdapter(private var pageList: ArrayList<PageItem>) : RecyclerView.Adapter<MyInformPagerViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyInformPagerViewHolder {
        return MyInformPagerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_intro_pager_item, parent, false))
    }

    override fun getItemCount(): Int {
        return pageList.size
    }

    override fun onBindViewHolder(holder: MyInformPagerViewHolder, position: Int) {
        holder.bindWithView(pageList[position])
    }

}
