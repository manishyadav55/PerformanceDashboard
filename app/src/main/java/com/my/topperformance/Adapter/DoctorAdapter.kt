package com.my.topperformance.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.my.topperformance.R
import com.my.topperforming.data.Datamodel
import com.my.topperforming.data.RankLogo

class DoctorAdapter(
    private val context: Context,
    private var ranklogo: ArrayList<RankLogo>,
    var datamodellist:ArrayList<Datamodel>
) : RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        return DoctorViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.doctor_list,parent,false))
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val ranks =ranklogo[position]
        holder.doctorName.text="Name :"+datamodellist[position].Name
        holder.consultation.text="No. of consultation :"+datamodellist[position].consultation
        holder.appreciation.text= ranklogo[position].appreciation

    }

    override fun getItemCount(): Int {
        return datamodellist.size
    }
    inner class DoctorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var doctorName = itemView.findViewById<TextView>(R.id.doctorName)
        var consultation=itemView.findViewById<TextView>(R.id.consultation)
        var appreciation=itemView.findViewById<TextView>(R.id.appreciation)
    }
}