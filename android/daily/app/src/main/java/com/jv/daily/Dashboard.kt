package com.jv.daily

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.CardView
import android.view.View
import android.widget.TextView
import java.util.concurrent.ConcurrentHashMap
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import java.util.*
import com.firebase.jobdispatcher.*
import android.app.AlarmManager
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.app.PendingIntent
import android.util.Log


class Dashboard : AppCompatActivity(), View.OnClickListener {

    lateinit var tv1: TextView
    lateinit var tv2: TextView
    lateinit var tv3: TextView
    lateinit var tv4: TextView
    lateinit var tv5: TextView
    lateinit var tv6: TextView
    lateinit var tv7: TextView
    lateinit var tv8: TextView
    lateinit var tv9: TextView



    override fun onClick(p0: View?) {
        var intent = Intent(this, AddMoney::class.java)

        when (p0?.id) {
            R.id.cd1 -> {
                intent.putExtra("item", "books");
            }

            R.id.cd2 -> {
                intent.putExtra("item", "cloths");
            }

            R.id.cd3 -> {
                intent.putExtra("item", "medicine");
            }

            R.id.cd4 -> {
                intent.putExtra("item", "transport");
            }

            R.id.cd5 -> {
                intent.putExtra("item", "furniture");
            }

            R.id.cd6 -> {
                intent.putExtra("item", "recharge");
            }

            R.id.cd7 -> {
                intent.putExtra("item", "vegetables");
            }

            R.id.cd8 -> {
                intent.putExtra("item", "petrol");
            }

            R.id.cd9 -> {
                intent.putExtra("item", "electricity");
            }

        }


        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        dailyReset()



        var cd1 = findViewById<CardView>(R.id.cd1)
        var cd2 = findViewById<CardView>(R.id.cd2)
        var cd3 = findViewById<CardView>(R.id.cd3)
        var cd4 = findViewById<CardView>(R.id.cd4)
        var cd5 = findViewById<CardView>(R.id.cd5)
        var cd6 = findViewById<CardView>(R.id.cd6)
        var cd7 = findViewById<CardView>(R.id.cd7)
        var cd8 = findViewById<CardView>(R.id.cd8)
        var cd9 = findViewById<CardView>(R.id.cd9)

        tv1 = findViewById<TextView>(R.id.tv1)
        tv2 = findViewById<TextView>(R.id.tv2)
        tv3 = findViewById<TextView>(R.id.tv3)
        tv4 = findViewById<TextView>(R.id.tv4)
        tv5 = findViewById<TextView>(R.id.tv5)
        tv6 = findViewById<TextView>(R.id.tv6)
        tv7 = findViewById<TextView>(R.id.tv7)
        tv8 = findViewById<TextView>(R.id.tv8)
        tv9 = findViewById<TextView>(R.id.tv9)

//        var db = DatabaseHelper(this);
//        var allItems = db.getUsers()
//        total(allItems)

        cd1.setOnClickListener(this)
        cd2.setOnClickListener(this)
        cd3.setOnClickListener(this)
        cd4.setOnClickListener(this)
        cd5.setOnClickListener(this)
        cd6.setOnClickListener(this)
        cd7.setOnClickListener(this)
        cd8.setOnClickListener(this)
        cd9.setOnClickListener(this)
    }

    private fun dailyReset() {
        var alarmMgr = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, Reset::class.java)
        val alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.HOUR, 0)
        calendar.set(Calendar.AM_PM, Calendar.AM)

        var midNightMilli  = calendar.timeInMillis

        val diff = Calendar.getInstance().timeInMillis - midNightMilli


        if(diff > 0) {
            calendar.add(Calendar.DATE, 1)
        }

        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY, alarmIntent)

    }

    override fun onResume() {
        super.onResume()
        var db = DatabaseHelper(this);
        var allItems = db.getUsers()
        total(allItems)
    }

    fun total(allItems: List<Money>) {
        var list = ConcurrentHashMap<String, Int>()

        for (item in allItems) {
            var value = list.containsKey(item.item)
            if (value) {
                list.set(item.item, list.getValue(item.item) + (item.amount * item.count))
            } else {
                list.set(item.item, (item.amount * item.count))
            }
        }

//        for (item in allItems) {
//            if (list.size > 0) {
//                for (l in list) {
//                    if (l.key == item.item) {
//                        list.set(item.item, l.value + (item.amount * item.count))
//                    } else {
//                        list.set(item.item, item.amount * item.count)
//
//                    }
//                }
//            } else {
//                list.set(item.item, item.amount * item.count)
//            }
//
//        }

        for (lll in list) {

            when (lll.key) {
                "books" -> {
                    tv1.text = lll.value.toString()
                }

                "cloths" -> {
                    tv2.text = lll.value.toString()
                }

                "medicine" -> {
                    tv3.text = lll.value.toString()
                }

                "transport" -> {
                    tv4.text = lll.value.toString()
                }

                "furniture" -> {
                    tv5.text = lll.value.toString()
                }

                "recharge" -> {
                    tv6.text = lll.value.toString()
                }

                "vegetables" -> {
                    tv7.text = lll.value.toString()
                }

                "petrol" -> {
                    tv8.text = lll.value.toString()
                }

                "electricity" -> {
                    tv9.text = lll.value.toString()
                }
            }

        }
    }

    override fun onBackPressed() {
        val startMain = Intent(Intent.ACTION_MAIN)
        startMain.addCategory(Intent.CATEGORY_HOME)
        startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(startMain)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.dashboard, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_upload -> {
                UploadService().uploadData()
            }
        }

        return super.onOptionsItemSelected(item)
    }

}
