package com.jv.daily

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class MainActivity : AppCompatActivity() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val listView = findViewById(R.id.listView) as ListView
//        listView.adapter = MyCustomAdapter(this)




//        val calendarView = findViewById(R.id.calendarView) as CalendarView
//        var addButton = findViewById(R.id.add) as FloatingActionButton
//
//        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
//            //Note that months are indexed from 0. So, 0 means january, 1 means February, 2 means march etc.
//            val msg = "Selected date is " + dayOfMonth + "/" + (month + 1) + "/" + year
//            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
//            val intent = Intent(this, History::class.java)
//            startActivity(intent);
//        }
//
//        addButton.setOnClickListener {
//            val intent = Intent(this, Add::class.java)
//            startActivity(intent);
//        }




//        val addIV = findViewById(R.id.add) as ImageView
//        val listIV = findViewById(R.id.list) as ImageView
//        val sendIV = findViewById(R.id.send) as ImageView
//        // set on-click listener
//        addIV.setOnClickListener {
//            // your code to perform when the user clicks on the ImageView
//            Toast.makeText(this@MainActivity, "You clicked on ImageView.", Toast.LENGTH_SHORT).show()
//            val intent = Intent(this, Add::class.java)
//            startActivity(intent);
//        }
//
//        listIV.setOnClickListener {
//            val intent = Intent(this, History::class.java);
//            startActivity(intent);
//        }
   // }

//    private class MyCustomAdapter(context: Context): BaseAdapter() {
//        private val mContext = context
//        var etAmount: EditText?=null
//
//        var icons: IntArray = intArrayOf(R.drawable.books, R.drawable.medicine, R.drawable.public_transportation, R.drawable.vegetables, R.drawable.clothes, R.drawable.furniture,
//                R.drawable.current, R.drawable.petrol, R.drawable.mobile_recharge);
//
//        override fun getCount(): Int {
//            return icons.size;
//        }
//
//        override fun getItemId(position: Int): Long {
//            return position.toLong();
//        }
//
//        override fun getItem(position: Int): Any {
//            return "String";
//        }
//
//        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//            val layoutInfalter = LayoutInflater.from(mContext);
//            var rowMain = layoutInfalter.inflate(R.layout.row_only_icons, parent, false);
//
//            var iv = rowMain.findViewById<ImageView>(R.id.iv)
//            iv.setImageResource(icons[position])
//
//            rowMain.setOnClickListener {
//
//                mContext.startActivity(Intent(mContext, AddMoney::class.java))
//
////                etAmount= EditText(mContext)
////                etAmount!!.hint="in RS"
////                etAmount!!.inputType = InputType.TYPE_CLASS_NUMBER
////
////                val builder = AlertDialog.Builder(mContext)
////
////                // Set the alert dialog title
////                builder.setTitle("AMOUNT")
////
////                // Display a message on alert dialog
////                builder.setMessage("Please enter the amount")
////
////                // Set a positive button and its click listener on alert dialog
////                builder.setPositiveButton("OK"){dialog, which ->
////                    // Do something when user press the positive button
////                    Toast.makeText(mContext,"Saved.",Toast.LENGTH_SHORT).show()
////                }
////
////                // Display a neutral button on alert dialog
////                builder.setNeutralButton("Cancel"){_,_ ->
////                    Toast.makeText(mContext,"Cancelled.",Toast.LENGTH_SHORT).show()
////                }
////
////                // Finally, make the alert dialog using builder
////                val dialog: AlertDialog = builder.create()
////                dialog.setView(etAmount, 50 ,0, 50 , 0)
////                // Display the alert dialog on app interface
////                dialog.show()
//            }
//
//            return rowMain;
//        }
//    }
}
