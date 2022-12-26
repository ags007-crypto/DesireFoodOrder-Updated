package com.firstapp.desirefoodorder

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firstapp.chatwithtutor.Cst_model
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Vieworder_adapter(private val context: Context, userModelList: ArrayList<Cst_model>) :
    RecyclerView.Adapter<Vieworder_adapter.MyViewHolder>() {
    private val userModelList: ArrayList<Cst_model>

    init {
        this.userModelList = userModelList
    }

    fun add(cst_model: Cst_model) {
        userModelList.add(cst_model)
        notifyDataSetChanged()
    }

    fun clear() {
        userModelList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_orderview, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val cst_model: Cst_model = userModelList[position]
        holder.name.setText(cst_model.name)
        holder.mob.setText(cst_model.phone)



    }

    override fun getItemCount(): Int {
        return userModelList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView
        val mob: TextView

        init {
            name = itemView.findViewById(R.id.cst_name)
            mob = itemView.findViewById(R.id.cst_mob)
        }
    }
}