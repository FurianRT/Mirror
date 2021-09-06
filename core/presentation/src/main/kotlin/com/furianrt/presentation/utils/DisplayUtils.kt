package com.furianrt.presentation.utils

import android.content.res.Resources
import kotlin.math.roundToInt

object DisplayUtils {

    fun dpToPx(dp: Float): Int = (dp * Resources.getSystem().displayMetrics.density).roundToInt()
}