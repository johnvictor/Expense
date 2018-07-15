package com.jv.daily

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.view.View
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class Login : AppCompatActivity() {

    lateinit var mCallbacks : PhoneAuthProvider.OnVerificationStateChangedCallbacks
    lateinit var mAuth: FirebaseAuth
    lateinit var houseId: String
    lateinit var phoneNo: String
    lateinit var name: String
    lateinit var nameEt: EditText
    lateinit var phoneEt: EditText
    lateinit var houseEt: EditText
    lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var loginBtn = findViewById<Button>(R.id.login)
        var linearParent = findViewById<LinearLayout>(R.id.parentLinear)

        progressBar = findViewById<ProgressBar>(R.id.progressBar1)
        nameEt = findViewById<EditText>(R.id.name)
        phoneEt = findViewById<EditText>(R.id.phone)
        houseEt = findViewById<EditText>(R.id.houseId)

        loginBtn.setOnClickListener {
            loginAction()
        }

        mAuth = FirebaseAuth.getInstance()
        setupUI(linearParent)
    }

    private fun loginAction() {
        phoneNo = phoneEt.text.toString()
        houseId = houseEt.text.toString()
        name = nameEt.text.toString()

        if(nameEt.text.toString().length === 0) {
            showToast("Please enter your name")
            return
        }

        if(phoneNo.length != 10) {
            showToast("Please enter valid mobile number")
            return
        }

        if(houseId.length === 0) {
            showToast("Please enter valid house id")
            return
        }

        showLoader()
        setupPhoneAuth("+91$phoneNo")

    }

    private fun setupPhoneAuth(phoneNo: String){
        verificationCallbacks()

        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNo, 60, TimeUnit.SECONDS, this, mCallbacks)
    }

    private fun verificationCallbacks() {
        val mContext: Context = this

        mCallbacks = object: PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                signIn(credential)
            }

            override fun onVerificationFailed(p0: FirebaseException?) {
                hideLoader()
                Toast.makeText(mContext, "Verification failed." + p0?.message, Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun signIn(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener{
                    task: Task<AuthResult> ->
                    if(task.isSuccessful) {

                        var myPreference = MyPreference(this)
                        myPreference.setHouseId(houseId)
                        myPreference.setPhone(phoneNo)

                        var uploadService = UploadService()
                        uploadService.sendUserDetails(name, phoneNo, houseId)

                        hideLoader()
                        Toast.makeText(this, "Logged in successfully.", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, Dashboard::class.java))
                    } else {
                        hideLoader()
                        Toast.makeText(this, "Login failed." + task.exception?.message, Toast.LENGTH_SHORT).show()
                    }

                }
    }

    private fun setupUI(view: View) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (view !is EditText) {
            view.setOnTouchListener { _, _ ->
                hideSoftKeyboard(this@Login)
                false
            }
        }

        //If a layout container, iterate over children and seed recursion.
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                setupUI(innerView)
            }
        }
    }

    fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager!!.hideSoftInputFromWindow(
                activity.currentFocus!!.windowToken, 0)
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun showLoader() {
        progressBar.visibility = View.VISIBLE
//        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    private fun hideLoader() {
        progressBar.visibility = View.GONE
//        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
}
