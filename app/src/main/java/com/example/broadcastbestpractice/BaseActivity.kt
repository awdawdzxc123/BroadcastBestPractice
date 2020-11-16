package com.example.broadcastbestpractice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity:AppCompatActivity(){
    lateinit var receiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //凡是继承本类的Activity均会被加入Activity管理器中
        ActivityCollector.addActvity(this)
    }

    override fun onResume() {
        super.onResume()
        val  intentFilter = IntentFilter("com.example.broadcastbestpractice.FORCS_OFFLINE")
        receiver =ForceOfflineReceiver()
        registerReceiver(receiver,intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActvity(this)
    }
    inner class ForceOfflineReceiver:BroadcastReceiver(){
        override fun onReceive(context:Context, intent:Intent) {
           AlertDialog.Builder(context).apply {
               setTitle("强制下线通知")
               setMessage("你的QQ已被下线,若非本人操作,请尽快修改密码")
               setCancelable(false)
               setPositiveButton("确定"){_,_->
                   //销毁所有Activity
                   ActivityCollector.finishAll()
                   //重新开启登录界面
                  val i =Intent(context,LoginActivity::class.java)
                   context.startActivity(i)
               }
               show()
           }
        }

    }
}