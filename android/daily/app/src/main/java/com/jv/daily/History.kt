package com.jv.daily

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView

class History : AppCompatActivity() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_history)
//
//
//        val listView = findViewById(R.id.listView) as ListView
//        listView.adapter = MyCustomAdapter(this)
//    }
//
//    private class MyCustomAdapter(context: Context): BaseAdapter() {
//        private val mContext = context
//
//        var icons: IntArray = intArrayOf(R.drawable.books, R.drawable.medicine, R.drawable.public_transportation, R.drawable.vegetables, R.drawable.clothes, R.drawable.furniture,
//                                            R.drawable.current, R.drawable.petrol, R.drawable.mobile_recharge);
//        var amount: IntArray = intArrayOf(55, 150, 30, 46, 5, 150, 30, 46, 5, 150, 30, 46, 5, 150, 30, 46, 5, 150, 30, 46, 5, 150, 30, 46, 5, 150, 30, 46, 5, 150, 30, 46, 5, 150, 30, 46);
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
//            var rowMain = layoutInfalter.inflate(R.layout.row_main, parent, false);
//
//            var iv = rowMain.findViewById<ImageView>(R.id.iv)
//            var tvMoney = rowMain.findViewById<TextView>(R.id.tvMoney)
//            tvMoney.text = "Rs: " +amount[position];
//            iv.setImageResource(icons[position])
//
//            return rowMain;
//        }
//    }
}
