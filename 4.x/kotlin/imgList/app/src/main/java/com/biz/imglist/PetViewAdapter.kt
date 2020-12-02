package com.biz.imglist

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PetViewAdapter(private val petList: ArrayList<PetVO>, private val context: Context) :
    RecyclerView.Adapter<PetViewAdapter.PetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pet_item, parent, false)
        return PetViewHolder(view)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        holder.txtName.text = petList[position]?.name
        holder.txtAge.text = petList[position]?.age.toString()
        holder.txtGender.text = petList[position]?.gender

        if (petList[position].photo != "") {
            // 이미지 이름에서 ID 값 추출하기
            val resourceId = context.resources.getIdentifier(
                petList[position].photo,
                "drawable",
                context.packageName
            )
            holder.imgPhoto.setImageResource(resourceId)
        } else {
            // 이미지가 없으면 안드로이드 기본 icon 으로 보여주기
            holder.imgPhoto.setImageResource(R.mipmap.ic_launcher)
        }
    }

    override fun getItemCount(): Int {
        return petList.size
    }

    class PetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtName: TextView = itemView.findViewById(R.id.pet_item_name)
        var txtAge: TextView = itemView.findViewById(R.id.pet_item_age)
        var txtGender: TextView = itemView.findViewById(R.id.pet_item_gender)
        var imgPhoto: ImageView = itemView.findViewById(R.id.pet_item_img)
    }
}