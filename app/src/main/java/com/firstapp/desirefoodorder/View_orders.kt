package com.firstapp.desirefoodorder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firstapp.chatwithtutor.Cst_model
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class View_orders : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var vieworder_adapter: Vieworder_adapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_orders)
        var databaseReference: DatabaseReference?=null
        var list = ArrayList<Cst_model>()
        recyclerView = findViewById<RecyclerView>(R.id.Recyclerview) as RecyclerView
         vieworder_adapter = Vieworder_adapter(this,list)
        recyclerView!!.setAdapter(vieworder_adapter!!)
        recyclerView!!.setLayoutManager(LinearLayoutManager(this))
        recyclerView!!.setHasFixedSize(true)
        val order_type = intent.getStringExtra("Order_type")
        if(order_type.equals("breakfast")){
             databaseReference = FirebaseDatabase.getInstance().getReference("customer_breakfast")

        }
        else if(order_type.equals("lunch")){
             databaseReference = FirebaseDatabase.getInstance().getReference("customer_lunch")

        }
        else if(order_type.equals("dinner")){
             databaseReference = FirebaseDatabase.getInstance().getReference("customer_dinner")

        }


        databaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                vieworder_adapter!!.clear()
                for (dataSnapshot in snapshot.children) {
                    val uid = dataSnapshot.key
                    val cstModel: Cst_model? = dataSnapshot.getValue(Cst_model::class.java)
                            list.add(cstModel!!)

                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })

    }
}






