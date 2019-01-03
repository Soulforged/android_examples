package io.personal.dicardo.myinstagram

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.TextView
import com.parse.ParseAnalytics
import com.parse.ParseUser

class MainActivity : AppCompatActivity() {

    private fun goToHome() {
        val toHome = Intent(this, Home::class.java)
        startActivity(toHome)
    }

    private fun goToList() {
        val toList = Intent(this, UserList::class.java)
        startActivity(toList)
    }

    private fun doSignUp() {
        val usernameTxt : TextView = findViewById(R.id.usernameTxt)
        val passwordTxt : TextView = findViewById(R.id.passwordTxt)
        val errorTxt : TextView = findViewById(R.id.errorTxt)
        val newUser = ParseUser()
        newUser.username = usernameTxt.text.toString()
        newUser.setPassword(passwordTxt.text.toString())
        newUser.signUpInBackground { e ->
            if (e !== null){
                Log.e("Parse error", e.message, e)
                errorTxt.text = e.localizedMessage
            } else {
                goToHome()
            }
        }
    }

    private fun doSignIn() {
        val usernameTxt : TextView = findViewById(R.id.usernameTxt)
        val passwordTxt : TextView = findViewById(R.id.passwordTxt)
        val errorTxt : TextView = findViewById(R.id.errorTxt)
        ParseUser.logInInBackground(usernameTxt.text.toString(), passwordTxt.text.toString()) { user, e ->
            if (e !== null) {
                Log.e("Parse error", e.message, e)
                errorTxt.text = e.localizedMessage
            } else if (user != null){
                goToHome()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ParseAnalytics.trackAppOpenedInBackground(intent)

        val signInBtn : TextView = findViewById(R.id.signInBtn)
        val signUpBtn : Button = findViewById(R.id.signUpBtn)

        val usernameTxt : TextView = findViewById(R.id.usernameTxt)
        val passwordTxt : TextView = findViewById(R.id.passwordTxt)

        if (ParseUser.getCurrentUser() != null) {
            goToHome()
        }

        val usrPassWatcher = object : TextWatcher {
            override fun afterTextChanged(a: Editable?) {
                val enabled = !usernameTxt.text.isBlank() && !passwordTxt.text.isBlank()
                signInBtn.isEnabled = enabled
                signUpBtn.isEnabled = enabled
            }

            override fun beforeTextChanged(a: CharSequence?, b: Int, c: Int, d: Int) {
            }

            override fun onTextChanged(a: CharSequence?, b: Int, c: Int, d: Int) {
            }
        }

        usernameTxt.addTextChangedListener(usrPassWatcher)
        passwordTxt.addTextChangedListener(usrPassWatcher)
        val function: (TextView, Int, KeyEvent?) -> Boolean = { _, actionId, event ->
            if (EditorInfo.IME_ACTION_DONE == actionId
                || event != null
                && event.keyCode == KeyEvent.KEYCODE_ENTER
                && event.action == KeyEvent.ACTION_UP) {
                doSignIn()
            }
            true
        }
        passwordTxt.setOnEditorActionListener(function)

        signUpBtn.setOnClickListener { doSignUp() }
        signInBtn.setOnClickListener { doSignIn() }
    }
}

