package com.jv.daily

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import java.text.SimpleDateFormat
import java.util.*

class UploadService {
    fun uploadData() {
        // Write a message to the database
        val database = FirebaseDatabase.getInstance()

        val usersRef = database.getReference("users")
        val expenseRef = database.getReference("expense")

//        val userId = usersRef.push().key
//        val expenseId = expenseRef.push().key

        var user = DBUserModel("Kumar", "9629670097")
        usersRef.child(user.phone).setValue(user)

        val c = Calendar.getInstance().time
        val df = SimpleDateFormat("dd-MM-yyyy")
        val formattedDate = df.format(c)

        val context = HashMap<String, Int>()
        context.put("vegetables", 30)
        context.put("petrol", 1200)

        var expense = DBExpenseModel("6532", context, "12-07-2018")
        expenseRef.child(expense.phone + "/" + expense.createdDate).setValue(expense)

    }
}