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
import com.crashlytics.android.Crashlytics
import com.karolapp.ideaappkt.R
import com.karolapp.ideaappkt.di.Component.DaggerCryptocurrencyComponent
import com.karolapp.ideaappkt.di.Module.CryptocurrencyModule
import com.karolapp.ideaappkt.model.Cryptocurrency
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WorkerManager(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {
    private val subscriptions = CompositeDisposable()
    val CHANNEL_ID = "1"
    override fun doWork(): Result {
        Log.i("try", "s")
        return try {
         //   checkCurrencyValue()
            Log.i("try", "s2")
            Result.success()
        } catch (ex: Exception) {
            Log.e("exep", ex.toString())
            Crashlytics.logException(ex)
            Result.failure()
        }

    }

    private fun checkCurrencyValue() {
        Log.i("to", "do")
       // var listCurrency: List<Cryptocurrency> = emptyList()
        val service = DaggerCryptocurrencyComponent.builder().cryptocurrencyModule(
            CryptocurrencyModule()
        ).build().gerCryptoService()
        val currency = service.getCryptocurrency()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                Log.i("value work manager", it.cryptocurrencyList!!.toString())
                //listCurrency = it.cryptocurrencyList!!
            }, { throwable ->
                Crashlytics.logException(throwable)
            })
//        if (!listCurrency.isEmpty()) {
//            for (item in listCurrency) {
//                Log.i("value work manager21", item.getRate().toString())
//                if (item.name!!.toLowerCase().contains("usd")) {
////                if (item.getRate()!!.toDouble() < 9000)
////                    displayNotification()
////                else if (item.getRate()!!.toDouble() > 9000)
////                    displayNotification()
//                    Log.i("value work manager2", item.getRate().toString())
//                }
//            }
//        }
        subscriptions.add(currency)
    }

    private fun displayNotification() {
        Log.i("worker", "background")
        var notificationId = 1
        var builder = NotificationCompat.Builder(this.applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle("Currency - Alert")
            .setContentText("The currency exceeds the set value")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("The currency exceeds the set value...")
            )
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