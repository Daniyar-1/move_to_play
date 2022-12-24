package com.movetoplay.services

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.util.Log
import com.google.gson.reflect.TypeToken
import com.movetoplay.data.model.user_apps.UsedTimeBody
import com.movetoplay.domain.model.user_apps.Limited
import com.movetoplay.domain.model.user_apps.UserApp
import com.movetoplay.domain.repository.UserAppsRepository
import com.movetoplay.pref.AccessibilityPrefs
import com.movetoplay.pref.Pref
import com.movetoplay.util.parseArray
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class PeriodicSyncAlarmManager : HiltBroadcastReceiver() {
    private val tag = "periodic"
    private val job = Job()
    private val serviceScope = CoroutineScope(Dispatchers.IO + job)

    @Inject
    lateinit var api: UserAppsRepository

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        syncData()
    }

    private fun syncData() {
        Log.e(tag, "periodic sync!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ")
        serviceScope.launch {
            val apps = ArrayList<UserApp>()
            AccessibilityPrefs.getLimitedAppsById(Pref.childId)?.let {
                parseArray<ArrayList<UserApp>>(it, object : TypeToken<ArrayList<UserApp>>() {}.type)
            }?.let { apps.addAll(it) }

            if (apps.isNotEmpty()) {
                apps.map { app ->
                    val usedApp = AccessibilityPrefs.getUsedTimeById(app.id)
                    if (usedApp != null) {
                        val min: Long = TimeUnit.MILLISECONDS.toMinutes(usedApp.usedTime)
                        if (min != 0L) {
                            try {
                                val result = api.setAppUsedTime(app.id, UsedTimeBody(min.toInt()))
                                if (result) {
                                    AccessibilityPrefs.clearUsedTimeById(app.id)
                                }
                                Log.e("access", "syncData: $result")
                            } catch (e: Throwable) {
                                Log.e("access", "syncData error: ${e.message}")
                            }
                        }
                    }
                }
            }
        }
    }

    fun setAlarm(context: Context) {
        Log.e(tag, "setAlarm: ")
        val requestCode = 1234
        val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, PeriodicSyncAlarmManager::class.java)
        val pi = PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        with(am) {
            //Устанавливаем интервал срабатывания every 15 min
            val calendar: Calendar = Calendar.getInstance()
            calendar.set(Calendar.MINUTE, 2)

            setInexactRepeating(
                AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime(),
                calendar.timeInMillis,
                pi
            )
        }
    }

    fun cancelAlarm(context: Context) {
        val intent = Intent(context, PeriodicSyncAlarmManager::class.java)
        val sender =
            PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_CANCEL_CURRENT
            )
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as (AlarmManager)
        alarmManager.cancel(sender)
        //Отменяем будильник, связанный с интентом данного класса
    }
}