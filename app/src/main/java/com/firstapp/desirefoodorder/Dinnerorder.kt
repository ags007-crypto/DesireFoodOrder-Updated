package com.firstapp.desirefoodorder

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firstapp.foodorder.model.customerorder
import com.google.firebase.database.*
import com.rengwuxian.materialedittext.MaterialEditText
import java.util.*
@Suppress("DEPRECATION")

class Dinnerorder : AppCompatActivity() {
    var edtPhone: MaterialEditText? = null
    var edtName: MaterialEditText? = null
    var edtType: MaterialEditText? = null
    var btnOrder: Button? = null
    var tst: Int? = 0
    var name = "Malviya Nagar Home Food"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dinnerorder)
        edtPhone = findViewById<View>(R.id.editPhone) as MaterialEditText
        edtName = findViewById<View>(R.id.editName) as MaterialEditText
        edtType = findViewById<View>(R.id.editType) as MaterialEditText
        btnOrder = findViewById<View>(R.id.btnOrder) as Button

        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val table_user: DatabaseReference = database.getReference("customer_dinner")
        btnOrder!!.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            //set title for alert dialog
            builder.setTitle("Confirmation")
            //set message for alert dialog
            builder.setMessage("Do you confirm your Order?")
            builder.setIcon(android.R.drawable.ic_dialog_alert)

            //performing positive action
            builder.setPositiveButton("Yes"){dialogInterface, which ->



                        if (edtName!!.length() > 0 && edtPhone!!.length() >= 10) {
                            val userbf = customerorder(edtName!!.text.toString(),edtPhone!!.text.toString(), edtType!!.text.toString())
                            table_user.child(edtPhone!!.text.toString()).setValue(userbf)
                            val pd = ProgressDialog(this@Dinnerorder)
                            pd.setMessage("Placing your Order...")
                            pd.show()
                            val handler = Handler()
                            handler.postDelayed({
                                pd.dismiss()
                                Toast.makeText(
                                    this@Dinnerorder,
                                    "Ordered Successfully!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }, 3000)

                        } else {
                            Toast.makeText(
                                this@Dinnerorder,
                                "Please enter all the fields properly.",
                                Toast.LENGTH_SHORT
                            ).show()

                            tst = 0

                        }

            }
            //performing cancel action

            //performing negative action
            builder.setNegativeButton("No"){dialogInterface, which ->
                Toast.makeText(this@Dinnerorder,"clicked No",Toast.LENGTH_LONG).show()
            }
            // Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(true)
            alertDialog.show()
        }
    }








}