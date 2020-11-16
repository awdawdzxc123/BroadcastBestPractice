package com.example.broadcastbestpractice

import android.app.Activity
/** Activity管理器...*/
object ActivityCollector {
    private val activities =ArrayList<Activity>()

    /** 添加指定的Activity...*/
    fun addActvity(activity: Activity){
        activities.add(activity)
    }
    /** 移除指定的Activity...*/
    fun removeActvity(activity: Activity){
        activities.remove(activity)
    }
    /** 清空所以Activity...*/
    fun finishAll(){
        for (actvity in activities){
            if (!actvity.isFinishing){
                actvity.finish()
            }
        }
        activities.clear()
    }

}