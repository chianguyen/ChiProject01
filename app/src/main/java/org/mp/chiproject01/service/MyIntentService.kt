package org.mp.chiproject01.service

import android.app.IntentService
import android.content.Intent
import android.widget.Toast

class MyIntentService: IntentService("mythread") {

    override fun onHandleIntent(intent: Intent?) {
        for(i in 1..10){
            Thread.sleep(1000)
        }
    }

    override fun onCreate() {
        super.onCreate()

        Toast.makeText(this, "Intent Service CREATED", Toast.LENGTH_SHORT).show()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Intent Service STARTED" ,Toast.LENGTH_LONG).show()
        return super.onStartCommand(intent, flags, startId)
    }



}