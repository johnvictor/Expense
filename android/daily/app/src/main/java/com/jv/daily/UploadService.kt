package com.jv.daily

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class UploadService {

    val database = FirebaseDatabase.getInstance()

    fun sendUserDetails(name: String, phone: String, house_id: String) {
        val usersRef = database.getReference("users")
        var user = DBUserModel(name, phone, house_id)
        usersRef.child(user.phone).setValue(user)
    }

    fun uploadData(data: HashMap<String, Int>, phoneNo: String) {
        // Write a message to the database
        val expenseRef = database.getReference("expense")

//        val userId = usersRef.push().key
//        val expenseId = expenseRef.push().key


        val formattedDate = Common().getDate()
//
//        val context = HashMap<String, Int>()
//        context.put("vegetables", 30)
//        context.put("petrol", 1200)

        var expense = DBExpenseModel(phoneNo, data, formattedDate)
        expenseRef.child(expense.phone + "/" + expense.createdDate).setValue(expense)

    }
}