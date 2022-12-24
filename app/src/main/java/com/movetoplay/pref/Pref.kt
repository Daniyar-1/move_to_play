package com.movetoplay.pref

import com.movetoplay.App

object Pref {
    private val sharedPreferences
        get() = App.sharedPrefs

    var isFirst: Boolean
        get() = sharedPreferences.getBoolean("isFir", true)
        set(value) = sharedPreferences.edit().putBoolean("isFir", value).apply()

    var userLogin: String
        get() = sharedPreferences.getString("login", "").toString()
        set(value) = sharedPreferences.edit().putString("login", value).apply()

    var childName: String
        get() = sharedPreferences.getString("childName", "").toString()
        set(value) = sharedPreferences.edit().putString("childName", value).apply()

    var childAge: String
        get() = sharedPreferences.getString("childAge", "").toString()
        set(value) = sharedPreferences.edit().putString("childAge", value).apply()

    var gender: String
        get() = sharedPreferences.getString("gender", "").toString()
        set(value) = sharedPreferences.edit().putString("gender", value).apply()

    var toast: String
        get() = sharedPreferences.getString("toast", "").toString()
        set(value) = sharedPreferences.edit().putString("toast", value).apply()

    var either_new_or_old: String
        get() = sharedPreferences.getString("either_new_or_old", "").toString()
        set(value) = sharedPreferences.edit().putString("either_new_or_old", value).apply()

    var childId: String
        get() = sharedPreferences.getString("childId", "").toString()
        set(value) = sharedPreferences.edit().putString("childId", value).apply()

    var parentId: String
        get() = sharedPreferences.getString("id", "").toString()
        set(value) = sharedPreferences.edit().putString("id", value).apply()

    var userAccessToken: String
        get() = sharedPreferences.getString("userToken", userRefreshToken).toString()
        set(value) = sharedPreferences.edit().putString("userToken", value).apply()

    var childToken: String
        get() = sharedPreferences.getString("childToken", "").toString()
        set(value) = sharedPreferences.edit().putString("childToken", value).apply()

    var accountId: String
        get() = sharedPreferences.getString("accountId", "").toString()
        set(value) = sharedPreferences.edit().putString("accountId", value).apply()

    var jwtSessionToken: String
        get() = sharedPreferences.getString("jwtSessionToken", "").toString()
        set(value) = sharedPreferences.edit().putString("jwtSessionToken", value).apply()

    var isChild: Boolean
        get() = sharedPreferences.getBoolean("isChild", false)
        set(value) = sharedPreferences.edit().putBoolean("isChild", value).apply()

    var deviceId: String
        get() = sharedPreferences.getString("userDevice", "").toString()
        set(value) = sharedPreferences.edit().putString("userDevice", value).apply()

    var pose: String
        get() = sharedPreferences.getString("pose", "").toString()
        set(value) = sharedPreferences.edit().putString("pose", value).apply()

    var typeTouch: String
        get() = sharedPreferences.getString("type", "").toString()
        set(value) = sharedPreferences.edit().putString("type", value).apply()

    var countTouch: Int
        get() = sharedPreferences.getInt("count", 0)
        set(value) = sharedPreferences.edit().putInt("count", value).apply()

    var startUnixTimestampTouch: Int
        get() = sharedPreferences.getInt("startUnixTimestamp", 0)
        set(value) = sharedPreferences.edit().putInt("startUnixTimestamp", value).apply()

    var jumps: Int
        get() = sharedPreferences.getInt("jumps", 0)
        set(value) = sharedPreferences.edit().putInt("jumps", value).apply()

    var push_ups: Int
        get() = sharedPreferences.getInt("push_ups", 0)
        set(value) = sharedPreferences.edit().putInt("push_ups", value).apply()

    var sits: Int
        get() = sharedPreferences.getInt("sits", 0)
        set(value) = sharedPreferences.edit().putInt("sits", value).apply()

    var userRefreshToken: String
        get() = sharedPreferences.getString("userToken", "").toString()
        set(value) = sharedPreferences.edit().putString("userToken", value).apply()

    var unixTime: Long
        get() = sharedPreferences.getLong("unix_time", 0)
        set(value) = sharedPreferences.edit().putLong("unix_time", value).apply()
}
