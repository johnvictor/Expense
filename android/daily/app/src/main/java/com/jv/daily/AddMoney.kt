package com.jv.daily

import android.content.Context
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.*
import android.widget.*
import android.widget.Toast
import java.util.*

class AddMoney : AppCompatActivity() {

    var total: Int = 0

    private lateinit var item: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_money)

        item = intent.extras.getString("item")

        val listView = findViewById<ListView>(R.id.listView)
        val textView = findViewById<TextView>(R.id.amount)

        var itemDetails = DatabaseHelper(this).getByItem(item)
        for(item in itemDetails) {
//            Log.d("KEY " + item.item, "" + item.amount + ", " + item.count);
            total += (item.amount * item.count)
        }
        listView.adapter = MyCustomAdapter(this, textView, item, itemDetails, total)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.money, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_save -> {
                Toast.makeText(this, "Saved successfully!", Toast.LENGTH_SHORT)
                        .show()
                startActivity(Intent(this, Dashboard::class.java))
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private class MyCustomAdapter(context: Context, textView: TextView, item: String, allItems: List<Money>, total: Int): BaseAdapter() {
        private val mContext = context
        var etAmount: EditText?=null
        var capitals = hashMapOf<String, String>()
        var txt = textView
        var item = item
        var allItems = allItems
        var total = total

        var icons: IntArray = intArrayOf(R.drawable.r1, R.drawable.r2, R.drawable.r5, R.drawable.r10, R.drawable.r20, R.drawable.r50,
                R.drawable.r100, R.drawable.r200, R.drawable.r500, R.drawable.r1000, R.drawable.r2000);
        var amount: IntArray = intArrayOf(1, 2, 5, 10, 20, 50, 100, 200, 500, 1000, 2000);

        override fun getCount(): Int {
            return icons.size;
        }

        override fun getItemId(position: Int): Long {
            return position.toLong();
        }

        override fun getItem(position: Int): Any {
            return "String";
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val layoutInfalter = LayoutInflater.from(mContext);

            var rowMain = layoutInfalter.inflate(R.layout.row_add_money, parent, false);
            var imageView = rowMain.findViewById<ImageView>(R.id.imageView)
            var tvCount = rowMain.findViewById<TextView>(R.id.count)
//            var tvMultiple = rowMain.findViewById<TextView>(R.id.multiple)

            txt.text = total.toString()

            var loop = true

            for(item in allItems) {
                if((item.amount == amount[position]) && loop) {
                    tvCount.text = item.count.toString()
                }
            }


            var addButton = rowMain.findViewById<ImageButton>(R.id.add)
            addButton.tag = amount[position]

            var removeButton = rowMain.findViewById<ImageButton>(R.id.remove)
            removeButton.tag = amount[position]

            imageView.setImageResource(icons[position])

            addButton.setOnClickListener {
                v: View ->
//                Log.d("position", "" + v.tag)
                var count = Integer.parseInt(tvCount.text.toString())

                tvCount.text = ( count + 1).toString();

                var amount = v.tag as Int;

                var money = Money(item, amount, count + 1)

                DatabaseHelper(mContext).updateUser(money)

                total += amount;
                txt.text = total.toString()
            }

            removeButton.setOnClickListener {
                v: View ->
                var count = Integer.parseInt(tvCount.text.toString());
                if(count > 0) {

                    tvCount.text = (count - 1).toString();

                    var amount = v.tag as Int;

                    var money = Money(item, amount, count - 1)

                    DatabaseHelper(mContext).updateUser(money)

                    total -= v.tag as Int
                    txt.text = total.toString()
                }

            }

            return rowMain;
        }


    }

}
