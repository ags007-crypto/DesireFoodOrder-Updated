package com.firstapp.desirefoodorder

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.firstapp.foodorder.model.customerorder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    var btnLunch: Button? = null
    var btnBreakfast: Button? = null
    var btnDinner: Button? = null
    var txtSlogan: TextView? = null
    var owner_login: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnLunch = findViewById<View>(R.id.btnLunch) as Button
        btnBreakfast = findViewById<View>(R.id.btnBreakfast) as Button
        btnDinner = findViewById<View>(R.id.btnDinner) as Button
       var btnLunchclosed = findViewById<View>(R.id.btnLunchclosed) as Button
       var btnBreakfastclosed = findViewById<View>(R.id.btnBreakfastclosed) as Button
       var btnDinnerclosed = findViewById<View>(R.id.btnDinnerclosed) as Button
        txtSlogan = findViewById<View>(R.id.txtslogan) as TextView
owner_login=findViewById<View>(R.id.owner_login) as TextView
        //txtSlogan.setTypeface(face);
        val database2: FirebaseDatabase = FirebaseDatabase.getInstance()
        val table_user2: DatabaseReference = database2.getReference("Owner")
        table_user2.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot2: DataSnapshot) {
                var bf_open=dataSnapshot2.child("B Open").value
                if(bf_open!!.equals("0")){
                btnBreakfastclosed.visibility=View.VISIBLE
                btnBreakfast!!.visibility=View.INVISIBLE}
                else if(bf_open!!.equals("1")){
                    btnBreakfastclosed.visibility=View.INVISIBLE
                    btnBreakfast!!.visibility=View.VISIBLE}

                var l_open=dataSnapshot2.child("L Open").value
                if(l_open!!.equals("0")){
                    btnLunchclosed.visibility=View.VISIBLE
                    btnLunch!!.visibility=View.INVISIBLE}
                else if(l_open!!.equals("1")){
                    btnLunchclosed.visibility=View.INVISIBLE
                    btnLunch!!.visibility=View.VISIBLE}

                var d_menu=dataSnapshot2.child("D Open").value
                if(d_menu!!.equals("0")){
                    btnDinnerclosed.visibility=View.VISIBLE
                    btnDinner!!.visibility=View.INVISIBLE}
                else if(d_menu!!.equals("1")){
                    btnDinnerclosed.visibility=View.INVISIBLE
                    btnDinner!!.visibility=View.VISIBLE}


            }



            override fun onCancelled(error: DatabaseError) {}
        })
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val table_user: DatabaseReference = database.getReference("Owner")
        table_user.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var bf_menu=dataSnapshot.child("bf_menu").value
                btnBreakfast!!.setText("Breakfast\n"+"("+bf_menu+")")
                var l_menu=dataSnapshot.child("l_menu").value
                btnLunch!!.setText("Lunch\n"+"("+l_menu+")")
                var d_menu=dataSnapshot.child("d_menu").value
                btnDinner!!.setText("Dinner\n"+"("+d_menu+")")
                           }



            override fun onCancelled(error: DatabaseError) {}
        })
        fun TextView.underline() {
            paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
        }
        owner_login!!.underline()
       owner_login!!.setOnClickListener { handleLoginDialog() }
        btnLunch!!.setOnClickListener {
            val signIn = Intent(this@MainActivity, Lunchorder::class.java)
            startActivity(signIn)
        }
        btnBreakfast!!.setOnClickListener {
            val signUp = Intent(this@MainActivity, Breakfastorder::class.java)
            startActivity(signUp)
        }
        btnDinner!!.setOnClickListener {
            val Dinnerorder = Intent(this@MainActivity, Dinnerorder::class.java)
            startActivity(Dinnerorder)
        }
    }
    private fun handleLoginDialog() {
        val view: View = layoutInflater.inflate(R.layout.login_dialog, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(view).show()
        val loginBtn = view.findViewById<Button>(R.id.login) as Button
        val emailEdit = view.findViewById<EditText>(R.id.emailEdit) as EditText
        val passwordEdit = view.findViewById<EditText>(R.id.passwordEdit) as EditText
        loginBtn.setOnClickListener {
            FirebaseAuth
                .getInstance()
                .signInWithEmailAndPassword(emailEdit.text.toString().trim { it <= ' ' }, passwordEdit.text.toString())
                .addOnFailureListener {
                    Toast.makeText(
                        applicationContext,
                        "Enter Correct Email/Password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnSuccessListener {
                    val order_manage = Intent(this@MainActivity, manage_order::class.java)
                    startActivity(order_manage)
                }
        }
    }
}