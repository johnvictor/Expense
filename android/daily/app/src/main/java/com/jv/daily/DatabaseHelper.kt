package com.jv.daily

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

import java.util.ArrayList


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASENAME, null, DATABASEVERSION) {

    var tableName = "MONEY"

    override fun onCreate(p0: SQLiteDatabase?) {
        val query = "CREATE TABLE $tableName (item TEXT, amount INTEGER, count INTEGER)"

        p0!!.execSQL(query)
        Log.v("@@@WWE", " Table Created Successfully")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE MONEY")
        onCreate(p0)
    }

    companion object {
        private val DATABASENAME = "daily"
        private val DATABASEVERSION = 1
    }

    fun insertAmount(money: Money) {
        val db = this.writableDatabase
        var values = ContentValues()
        values.put("item", money.item)
        values.put("amount", money.amount)
        values.put("count", money.count)

        db.insert(tableName, null, values)
        db.close()
        Log.v("@@@WWe ", " Record Inserted Sucessfully")
    }

    fun getUsers(): List<Money> {
        val db = this.writableDatabase
        val list = ArrayList<Money>()
        val cusrsor: Cursor
        cusrsor = db.rawQuery("SELECT * FROM $tableName", null)
        if (cusrsor != null) {
            if (cusrsor.count > 0) {
                cusrsor.moveToFirst()
                do {
                    val amount = cusrsor.getInt(cusrsor.getColumnIndex("amount"))
                    val count = cusrsor.getInt(cusrsor.getColumnIndex("count"))
                    val item = cusrsor.getString(cusrsor.getColumnIndex("item"))

                    val meony = Money(item, amount, count)
                    list.add(meony)
                } while (cusrsor.moveToNext())
            }
        }
        return list
    }

    fun getByItem(itemName: String): List<Money> {
        val db = this.writableDatabase
        val list = ArrayList<Money>()
        val cusrsor: Cursor
        cusrsor = db.rawQuery("SELECT * FROM $tableName where item = '$itemName'", null)
        if (cusrsor != null) {
            if (cusrsor.count > 0) {
                cusrsor.moveToFirst()
                do {
                    val amount = cusrsor.getInt(cusrsor.getColumnIndex("amount"))
                    val count = cusrsor.getInt(cusrsor.getColumnIndex("count"))
                    val item = cusrsor.getString(cusrsor.getColumnIndex("item"))

                    val meony = Money(item, amount, count)
                    list.add(meony)
                } while (cusrsor.moveToNext())
            }
        }
        return list
    }

    fun updateUser(money: Money) {
        val db = this.writableDatabase
        var values = ContentValues()
        values.put("item", money.item)
        values.put("amount", money.amount)
        values.put("count", money.count)

        if(CheckIsDataAlreadyInDBorNot(money)) {
            db.update(tableName, values, "amount = " + money.amount + " AND item = '" + money.item + "'",null)
            Log.v("@@@WWe", " Record updated")
        } else {
            db.insert("MONEY", null, values)
            Log.v("@@@WWe", "Inserted")
        }

        db.close()
    }

    fun CheckIsDataAlreadyInDBorNot(money: Money): Boolean {
        val db = this.writableDatabase
        val query = "Select * from $tableName where item = '" + money.item + "' and amount = " + money.amount
        val cursor = db.rawQuery(query, null)
        if (cursor.count <= 0) {
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }

    fun clearMoney() {
        val db = this.writableDatabase
        db.execSQL("delete from $tableName")
    }

//    fun deleteUser(users: Users) {
//        val db = this.writableDatabase
//        var values = ContentValues()
//        values.put("userID", users.userID)
//        values.put("userName", users.userName)
//        values.put("userAge", users.userAge)
//        val retVal = db.delete("USER", "userID = " + users.userID, null)
//        if (retVal >= 1) {
//            Log.v("@@@WWe", " Record deleted")
//        } else {
//            Log.v("@@@WWe", " Not deleted")
//        }
//        db.close()
//
//    }
}