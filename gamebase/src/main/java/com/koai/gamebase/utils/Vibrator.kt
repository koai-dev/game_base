package com.koai.gamebase.utils

import android.content.Context
import android.os.Vibrator

fun Context.vibrateError() {
    (getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator)?.vibrate(100)
}
