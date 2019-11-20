package com.faskn.app.weatherapp.utils.extensions

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.*
import android.util.Log

fun Any.logE(message: String) = Log.e(this::class.java.simpleName, message)

fun Any.logD(message: String) = Log.d(this::class.java.simpleName, message)

fun Any.logV(message: String) = Log.v(this::class.java.simpleName, message)

fun Any.logW(message: String) = Log.w(this::class.java.simpleName, message)

fun Any.logI(message: String) = Log.i(this::class.java.simpleName, message)

fun Any.emptyString() = ""

fun Any.tryCatch(
    tryBlock: () -> Unit,
    catchBlock: ((t: Throwable) -> Unit)? = null,
    finalBlock: (() -> Unit)? = null
) {
    try {
        tryBlock()
    } catch (e: Exception) {
        catchBlock?.invoke(e)
    } finally {
        finalBlock?.invoke()
    }
}

fun spannable(func: () -> SpannableString) = func()

private fun span(s: CharSequence, o: Any) = (
    if (s is String) SpannableString(s) else s as? SpannableString
        ?: SpannableString("")
    ).apply { setSpan(o, 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) }

operator fun SpannableString.plus(s: SpannableString) = SpannableString(TextUtils.concat(this, s))

operator fun SpannableString.plus(s: String) = SpannableString(TextUtils.concat(this, s))

fun bold(s: CharSequence) = span(s, StyleSpan(Typeface.BOLD))

fun italic(s: CharSequence) = span(s, StyleSpan(android.graphics.Typeface.ITALIC))

fun underline(s: CharSequence) = span(s, UnderlineSpan())

fun strike(s: CharSequence) = span(s, StrikethroughSpan())

fun sup(s: CharSequence) = span(s, SuperscriptSpan())

fun sub(s: CharSequence) = span(s, SubscriptSpan())

fun size(size: Float, s: CharSequence) = span(s, RelativeSizeSpan(size))

fun color(color: Int, s: CharSequence) = span(s, ForegroundColorSpan(color))

fun background(color: Int, s: CharSequence) = span(s, BackgroundColorSpan(color))

fun url(url: String, s: CharSequence) = span(s, URLSpan(url))
