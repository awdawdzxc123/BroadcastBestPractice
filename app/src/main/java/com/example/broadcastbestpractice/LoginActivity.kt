package com.example.broadcastbestpractice


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val prefs =getPreferences(Context.MODE_PRIVATE)
        val isRemember =prefs.getBoolean("remember_password",false)
        if (isRemember){
            val account =prefs.getString("User","")
            val pwd =prefs.getString("Pwd","")
            UserID.setText(account)
            UserPwd.setText(pwd)
            checkBoxpass.isChecked =true
        }

        LoginButton.setOnClickListener {
            val id:CharSequence=UserID.text.trim().toString()
            val pwd:String=UserPwd.text.trim().toString()

            if (id=="1105928109"&&pwd=="123456"){
                val editor =prefs.edit()
                if (checkBoxpass.isChecked){
                    editor.putBoolean("remember_password",true)
                    editor.putString("User",id.toString())
                    editor.putString("Pwd",pwd)
                }else{
                    editor.apply()
                }
                editor.apply()

                val intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"QQ号码或密码错误,请重试",Toast.LENGTH_LONG).show()
            }
        }

    }
}