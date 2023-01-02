package my.edu.tarc.assignment

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import java.util.*


class Notification : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val builder = context?.let {
//                NotificationCompat.Builder(it, "reminder")
//                    .setSmallIcon(R.drawable.ic_launcher_foreground)
//                    .setContentTitle("Alarm")
//                    .setContentText("This is a test notification")
//                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//            }
//
//            val channel = NotificationChannel("reminder", "Default", NotificationManager.IMPORTANCE_DEFAULT)
//
//            val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            if (builder != null) {
//                notificationManager.notify(1, builder.build())
//            }
//            notificationManager.createNotificationChannel(channel)
//        }
        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 4)
        calendar.set(Calendar.MINUTE, 1)
        calendar.set(Calendar.SECOND, 0)

        val notification = NotificationCompat.Builder(context, "reminder")
            .setSmallIcon(R.drawable.rewardlogo)
            .setContentTitle("Reminder")
            .setContentText("This is a reminder notification")
            .setWhen(calendar.timeInMillis)
            .setShowWhen(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        notificationManager.notify(1, notification)
    }
}