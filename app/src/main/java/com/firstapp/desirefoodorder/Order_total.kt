package com.firstapp.desirefoodorder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class Order_total : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_total)
        val bf_total = findViewById<View>(R.id.bf_total) as TextView
        val l_total = findViewById<View>(R.id.l_total) as TextView
        val d_total = findViewById<View>(R.id.d_total) as TextView

        val databaseRef = Firebase.database.getReference("customer_breakfast")
      databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                   bf_total.setText("Breakfast - "+dataSnapshot.childrenCount.toString())

            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
        val databaseRef2 = Firebase.database.getReference("customer_lunch")

        databaseRef2.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                l_total.setText("Lunch - "+dataSnapshot.childrenCount.toString())

            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
        val databaseRef3 = Firebase.database.getReference("customer_dinner")

        databaseRef3.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                d_total.setText("Dinner - "+dataSnapshot.childrenCount.toString())

            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })

        bf_total.setOnClickListener({
            val intent = Intent(this@Order_total, View_orders::class.java)
            intent.putExtra("Order_type","breakfast" )
            startActivity(intent)

        })
        l_total.setOnClickListener({
            val intent = Intent(this@Order_total, View_orders::class.java)
            intent.putExtra("Order_type","lunch" )
            startActivity(intent)

        })
        d_total.setOnClickListener({
            val intent = Intent(this@Order_total, View_orders::class.java)
            intent.putExtra("Order_type","dinner" )
            startActivity(intent)

        })


    }
}