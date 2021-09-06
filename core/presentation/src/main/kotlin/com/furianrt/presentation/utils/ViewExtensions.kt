package com.furianrt.presentation.utils

import android.view.View
import android.view.animation.Interpolator
import androidx.annotation.Dimension
import androidx.interpolator.view.animation.FastOutSlowInInterpolator

fun View.animateScaleXY(scale: Float, duration: Long = 100L) {
    if (scaleX != scale) {
        animate().scaleX(scale).duration = duration
    }
    if (scaleY != scale) {
        animate().scaleY(scale).duration = duration
    }
}

fun View.animateTranslationX(
    translation: Float?,
    duration: Long = 250L,
    interpolator: Interpolator = FastOutSlowInInterpolator()
) {

    if (translation == null) {
        return
    }

    if (translationX != translation) {
        animate().translationX(translation).setInterpolator(interpolator).duration = duration
    }
}

fun View.getCenterX() = x + width / 2f

fun View.setElevationDp(@Dimension(unit = Dimension.DP) elevation: Float) {
    val elevationPx = DisplayUtils.dpToPx(elevation).toFloat()
    if (this.elevation != elevationPx) {
        this.elevation = elevationPx
    }
}