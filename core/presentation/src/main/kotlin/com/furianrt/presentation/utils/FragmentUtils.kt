package com.furianrt.presentation.utils

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.SavedStateHandle
import java.lang.IllegalArgumentException

inline fun FragmentManager.inTransaction(action: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.action()
    fragmentTransaction.commit()
}

inline fun <reified T> Fragment.getArgs(): T = requireArguments().getParcelable("args")
    ?: throw IllegalArgumentException("Args required")

inline fun <reified T> SavedStateHandle.getArgs(): T = get<T>("args")
    ?: throw IllegalArgumentException("Args required")

inline fun <reified T : Parcelable> Fragment.withArgs(args: T): Fragment = apply {
    arguments = Bundle().apply { putParcelable("args", args) }
}