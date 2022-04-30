package com.lupa.core.util

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.os.bundleOf

fun Context.intentTo(className: String, bundle: Bundle = bundleOf()){
    Intent(this, Class.forName(className)).apply {
        putExtras(bundle)
        startActivity(this)
    }
}