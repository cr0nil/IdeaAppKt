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
    var listCurrency: List<Cryptocurrency> = emptyList()
    private val subscriptions = CompositeDisposable()
    val CHANNEL_ID = "1"
    override fun doWork(): Result {
        return try {
            val boundRate =  inputData.getDouble("boundRate",9000.0)
            val bound =  inputData.getString("bound")
            val currency =  inputData.getString("currency")
            checkCurrencyValue(boundRate,bound!!,currency!!)
            Result.success()
        } catch (ex: Exception) {
            Crashlytics.logException(ex)
            Result.failure()
        }

    }

    private fun checkCurrencyValue(boundRate : Double,bound: String,currency: String) {

        val service = DaggerCryptocurrencyComponent.builder().cryptocurrencyModule(
            CryptocurrencyModule()
        ).build().gerCryptoService()
        val currency = service.getCryptocurrency()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                listCurrency = it.cryptocurrencyList!!
                if (!listCurrency.isEmpty()) {
                    for (item in listCurrency) {
                        Log.i("value work manager21", item.getRate().toString())
                        if (item.name!!.toLowerCase().contains(currency)) {
                            when(bound.toInt()){
                                1->if (item.getRate()!!.toDouble() < boundRate)
                                    displayNotification()
                                2->if (item.getRate()!!.toDouble() > boundRate)
                                    displayNotification()
                            }

                        }
                    }
                }
            }, { throwable ->
                Crashlytics.logException(throwable)
            })

        subscriptions.add(currency)
    }

    private fun displayNotification() {

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