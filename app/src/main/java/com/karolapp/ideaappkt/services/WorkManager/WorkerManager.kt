package com.karolapp.ideaappkt.services.WorkManager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.karolapp.ideaappkt.R

class WorkerManager(context: Context,workerParameters: WorkerParameters) : Worker(context,workerParameters){
    val CHANNEL_ID = "1"
    override fun doWork(): Result {
        displayNotification()
        return Result.success()
    }

    private fun displayNotification() {
        Log.i("worker","background")
        var notificationId = 1
        var builder = NotificationCompat.Builder(this.applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle("Currency - Alert")
            .setContentText("The currency exceeds the set value")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("The currency exceeds the set value..."))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(applicationContext)) {
            if (getNotificationChannel(CHANNEL_ID) == null) {
                createNotificationChannel(applicationContext)
            }
            notify(notificationId, builder.build())
        }

    }
    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            NotificationManagerCompat.from(context).createNotificationChannel(channel)
        }
    }
}