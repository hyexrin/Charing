package com.hyexrin.chairing

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_intro_pager_item.view.*

class MyInformPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val itemImage = itemView.findViewById<ImageView>(R.id.pager_item_image)
    private val itemContent = itemView.pager_item_question
    private val itemBg = itemView.pager_item_bg

    fun bindWithView(pageItem: PageItem){
        itemImage.setImageResource(pageItem.imageSrc)
        itemContent.text = pageItem.content

        if(pageItem.bgColor != R.color.white){
            itemContent.setTextColor(Color.WHITE)
        }

        itemBg.setBackgroundResource(pageItem.bgColor)
    }

}
