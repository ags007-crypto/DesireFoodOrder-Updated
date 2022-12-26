package com.firstapp.desirefoodorder

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.firstapp.foodorder.model.customerorder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
@Suppress("DEPRECATION")

class manage_order : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_order)
        val menu_change = findViewById<Button>(R.id.menu_change) as Button
        val order_switch = findViewById<Button>(R.id.order_switch) as Button
        val view_order = findViewById<Button>(R.id.view_order) as Button
        val clear_order = findViewById<Button>(R.id.clear_order) as Button

        menu_change!!.setOnClickListener {
            handlemenuchange()
        }
        order_switch!!.setOnClickListener {
            handleorderswitch()
        }
        view_order!!.setOnClickListener {
            handlevieworder()
        }
        clear_order!!.setOnClickListener {
            val builder = android.app.AlertDialog.Builder(this)
            //set title for alert dialog
            builder.setTitle("Confirmation")
            //set message for alert dialog
            builder.setMessage("Are you sure you want to Clear Todays' Order?")
            builder.setIcon(android.R.drawable.ic_dialog_alert)

            //performing positive action
            builder.setPositiveButton("Yes"){dialogInterface, which ->

handleclearorder()
                val pd = ProgressDialog(this@manage_order)
                pd.setMessage("Clearing Todays' Orders...")
                pd.show()
                val handler = Handler()
                handler.postDelayed({
                    pd.dismiss()
                    Toast.makeText(
                        this@manage_order,
                        "Cleared Todays' Orders Successfully!",
                        Toast.LENGTH_SHORT
                    ).show()
                }, 3000)
            }

            //performing negative action
            builder.setNegativeButton("No"){dialogInterface, which ->
                Toast.makeText(this@manage_order,"Not Cleared!",Toast.LENGTH_LONG).show()
            }
            // Create the AlertDialog
            val alertDialog: android.app.AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(true)
            alertDialog.show()
        }


    }
    private fun handlemenuchange(){
        val view: View = layoutInflater.inflate(R.layout.menu_change, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(view).show()
        val menu_type = view.findViewById<RadioGroup>(R.id.menu_type) as RadioGroup
        val bf_menu = view.findViewById<RadioButton>(R.id.bf_menu) as RadioButton
        val l_menu = view.findViewById<RadioButton>(R.id.l_menu) as RadioButton
        val d_menu = view.findViewById<RadioButton>(R.id.d_menu) as RadioButton
        val input_menu = view.findViewById<EditText>(R.id.input_menu) as EditText
        val btn_change = view.findViewById<Button>(R.id.btn_change) as Button
btn_change!!.setOnClickListener({
    if(bf_menu.isChecked){
        val databaseRef = Firebase.database.getReference("Owner")
        databaseRef.child("bf_menu").setValue(input_menu.text.toString())
        Toast.makeText(
            this,
            "Changed Breakfast Menu Successfully!",
            Toast.LENGTH_SHORT
        ).show()}

    else if(l_menu.isChecked){
        val databaseRef = Firebase.database.getReference("Owner")
        databaseRef.child("l_menu").setValue(input_menu.text.toString())
        Toast.makeText(
            this,
            "Changed Lunch Menu Successfully!",
            Toast.LENGTH_SHORT
        ).show() }

    else if(d_menu.isChecked){
        val databaseRef = Firebase.database.getReference("Owner")
        databaseRef.child("d_menu").setValue(input_menu.text.toString())
        Toast.makeText(
            this,
            "Changed Dinner Menu Successfully!",
            Toast.LENGTH_SHORT
        ).show()  }
})

    }
    private fun handleorderswitch(){

        val view: View = layoutInflater.inflate(R.layout.order_close, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(view).show()
        val b_on = view.findViewById<Button>(R.id.b_on) as Button
        val l_on = view.findViewById<Button>(R.id.l_on) as Button
        val d_on = view.findViewById<Button>(R.id.d_on) as Button
        val b_off = view.findViewById<Button>(R.id.b_off) as Button
        val l_off = view.findViewById<Button>(R.id.l_off) as Button
        val d_off = view.findViewById<Button>(R.id.d_off) as Button
        val database2: FirebaseDatabase = FirebaseDatabase.getInstance()
        val table_user2: DatabaseReference = database2.getReference("Owner")
        table_user2.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot2: DataSnapshot) {
                var bf_open=dataSnapshot2.child("B Open").value
                if(bf_open!!.equals("0")){
                    b_on.visibility=View.INVISIBLE
                    b_off.visibility=View.VISIBLE}
                else if(bf_open!!.equals("1")){
                    b_on.visibility=View.VISIBLE
                    b_off.visibility=View.INVISIBLE}

                var l_open=dataSnapshot2.child("L Open").value
                if(l_open!!.equals("0")){
                    l_on.visibility=View.INVISIBLE
                    l_off.visibility=View.VISIBLE}
                else if(l_open!!.equals("1")){
                    l_on.visibility=View.VISIBLE
                    l_off.visibility=View.INVISIBLE}

                var d_open=dataSnapshot2.child("D Open").value
                if(d_open!!.equals("0")){
                    d_on.visibility=View.INVISIBLE
                    d_off.visibility=View.VISIBLE}
                else if(d_open!!.equals("1")){
                    d_on.visibility=View.VISIBLE
                    d_off.visibility=View.INVISIBLE}

            }



            override fun onCancelled(error: DatabaseError) {}
        })
        b_on!!.setOnClickListener({
            val databaseRef = Firebase.database.getReference("Owner")
            databaseRef.child("B Open").setValue("0")
            b_on.visibility=View.INVISIBLE
            b_off.visibility=View.VISIBLE
        })
        b_off!!.setOnClickListener({
            val databaseRef = Firebase.database.getReference("Owner")
            databaseRef.child("B Open").setValue("1")
            b_on.visibility=View.VISIBLE
            b_off.visibility=View.INVISIBLE
        })
        l_on!!.setOnClickListener({
            val databaseRef = Firebase.database.getReference("Owner")
            databaseRef.child("L Open").setValue("0")
            l_on.visibility=View.INVISIBLE
            l_off.visibility=View.VISIBLE
        })
        l_off!!.setOnClickListener({
            val databaseRef = Firebase.database.getReference("Owner")
            databaseRef.child("L Open").setValue("1")
            l_on.visibility=View.VISIBLE
            l_off.visibility=View.INVISIBLE
        })
        d_on!!.setOnClickListener({
            val databaseRef = Firebase.database.getReference("Owner")
            databaseRef.child("D Open").setValue("0")
            d_on.visibility=View.INVISIBLE
            d_off.visibility=View.VISIBLE
        })
        d_off!!.setOnClickListener({
            val databaseRef = Firebase.database.getReference("Owner")
            databaseRef.child("D Open").setValue("1")
            d_on.visibility=View.VISIBLE
            d_off.visibility=View.INVISIBLE
        })


    }
private fun handlevieworder(){
    val vieworder = Intent(this@manage_order, Order_total::class.java)
    startActivity(vieworder)

}
    private fun handleclearorder(){
        var datarefbf = FirebaseDatabase.getInstance().getReference("customer_breakfast")
        datarefbf.removeValue()
        var datarefl = FirebaseDatabase.getInstance().getReference("customer_lunch")
        datarefl.removeValue()
        var datarefd = FirebaseDatabase.getInstance().getReference("customer_dinner")
        datarefd.removeValue()

    }



    }
